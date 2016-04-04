package vn.creative.twitterlite.view.timeline.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.adapter.TimelineAdapter;
import vn.creative.twitterlite.common.CommonUtils;
import vn.creative.twitterlite.common.EndlessRecyclerViewScrollListener;
import vn.creative.twitterlite.common.VerticalSpaceItemDecoration;
import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.view.profile.UserProfileFrg;
import vn.creative.twitterlite.view.tweet.TweetDlg;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public class HomeTabFrg extends Fragment implements IHomeTabView, ITimelineActionListener, DialogInterface.OnDismissListener {
    private static final String TAG = HomeTabFrg.class.getSimpleName();

    @Bind(R.id.tab_home_swipe_layout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.tab_home_rv_timeline)
    RecyclerView rvTimeline;

    private long nCurID;

    private HomeTabPresenter homeTabPresenter;
    private TimelineAdapter timelineAdapter;
    private List<PostModel> mPosts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_home, null);
        ButterKnife.bind(this, view);
        homeTabPresenter = new HomeTabPresenter(this);

        // Configure the refreshing colors
        swipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (CommonUtils.isNetworkAvailable(getContext())) {
                    nCurID = 1;
                    homeTabPresenter.fetchTimeline(nCurID);

                } else {
                    swipeLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "Network error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTimeline.setLayoutManager(linearLayoutManager);
        rvTimeline.addItemDecoration(new VerticalSpaceItemDecoration(20));
//        rvTimeline.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.recyclerview_divider));
        timelineAdapter = new TimelineAdapter(getContext(), this);
        rvTimeline.setAdapter(timelineAdapter);

        rvTimeline.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (CommonUtils.isNetworkAvailable(getContext())) {
                    homeTabPresenter.fetchTimeline(nCurID);

                } else {
                    Toast.makeText(getContext(), "Network error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // First time load data
        nCurID = 1;
        if (CommonUtils.isNetworkAvailable(getContext())) {
            homeTabPresenter.fetchTimeline(nCurID);

        } else {
            List<PostModel> posts = new ArrayList<>();
//            for (DBPost dbPost : DBPost.getPosts()) {
//                PostModel post = new Gson().fromJson(dbPost.getPostJson(), PostModel.class);
//                if (post != null) {
//                    posts.add(post);
//                }
//            }
            onFetchTimelineSuccess(posts);
        }

        return view;
    }

    @Override
    public void onFetchTimelineSuccess(List<PostModel> posts) {
        swipeLayout.setRefreshing(false);
        mPosts.addAll(posts);

        if (nCurID == 1) {
            timelineAdapter.update(posts);

        } else {
            timelineAdapter.updateMore(posts);
        }

        if (!posts.isEmpty()) {
            nCurID = posts.get(posts.size() - 1).getId() - 1;
        }
    }

    @Override
    public void onFetchTimelineFail() {
        swipeLayout.setRefreshing(false);
        Toast.makeText(getContext(), "Get Twitter timeline fail!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(PostModel post) {
        Fragment fragment = new UserProfileFrg();
        Bundle bundle = new Bundle();
        bundle.putLong("id", post.getUser().getId());
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, "UserProfileFrg")
                .addToBackStack("UserProfileFrg").commit();
    }

    @Override
    public void onReplyClick(PostModel post) {
        TweetDlg dlg = new TweetDlg();
        Bundle bundle = new Bundle();
        bundle.putParcelable("post", post);
        dlg.show(getFragmentManager(), "TweetDlg");
    }

    @Override
    public void onRetweetClick(PostModel post) {

    }

    @Override
    public void onLikeClick(PostModel post) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        nCurID = 1;
        homeTabPresenter.fetchTimeline(nCurID);
    }
}
