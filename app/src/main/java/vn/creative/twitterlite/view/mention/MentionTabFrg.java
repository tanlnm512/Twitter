package vn.creative.twitterlite.view.mention;

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
import vn.creative.twitterlite.adapter.MentionAdapter;
import vn.creative.twitterlite.adapter.TimelineAdapter;
import vn.creative.twitterlite.common.CommonUtils;
import vn.creative.twitterlite.common.EndlessRecyclerViewScrollListener;
import vn.creative.twitterlite.model.PostModel;

/**
 * Created by tanlnm on 3/31/2016.
 */
public class MentionTabFrg extends Fragment implements IMentionTabView, IMentionActionListener {
    private static final String TAG = MentionTabFrg.class.getSimpleName();

    @Bind(R.id.tab_mention_swipe_layout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.tab_mention_rv_timeline)
    RecyclerView rvTimeline;

    private long nCurID = 1;

    private MentionTabPresenter mentionTabPresenter;
    private MentionAdapter mentionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_mention, container, false);
        ButterKnife.bind(this, view);

        mentionTabPresenter = new MentionTabPresenter(this);

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
                    mentionTabPresenter.fetchMention(nCurID);

                } else {
                    swipeLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "Network error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTimeline.setLayoutManager(linearLayoutManager);
        mentionAdapter = new MentionAdapter(getContext(), this);
        rvTimeline.setAdapter(mentionAdapter);

        rvTimeline.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (CommonUtils.isNetworkAvailable(getContext())) {
                    mentionTabPresenter.fetchMention(nCurID);

                } else {
                    Toast.makeText(getContext(), "Network error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // First time load data
        if (CommonUtils.isNetworkAvailable(getContext())) {
            mentionTabPresenter.fetchMention(nCurID);

        } else {
            List<PostModel> posts = new ArrayList<>();
//            for (DBPost dbPost : DBPost.getPosts()) {
//                PostModel post = new Gson().fromJson(dbPost.getPostJson(), PostModel.class);
//                if (post != null) {
//                    posts.add(post);
//                }
//            }
//            onFetchMentionSuccess(posts);
        }

        return view;
    }

    @Override
    public void onFetchMentionSuccess() {

    }

    @Override
    public void onFetchMentionFail() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick() {

    }
}
