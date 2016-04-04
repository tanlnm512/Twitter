package vn.creative.twitterlite.view.profile.tweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.adapter.TimelineAdapter;
import vn.creative.twitterlite.adapter.TweetsAdapter;
import vn.creative.twitterlite.common.VerticalSpaceItemDecoration;
import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.view.timeline.home.ITimelineActionListener;

/**
 * Created by tanlnm on 4/4/2016.
 */
public class TweetsTabFrg extends Fragment implements ITimelineActionListener {
    @Bind(R.id.tab_tweets_rv_timeline)
    RecyclerView rvTimeline;

    private static List<PostModel> mPosts;
    private TweetsAdapter tweetsAdapter;

    public static TweetsTabFrg newInstance(List<PostModel> posts) {
        mPosts = posts;
        return new TweetsTabFrg();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_tab_tweets, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTimeline.setLayoutManager(linearLayoutManager);
        rvTimeline.addItemDecoration(new VerticalSpaceItemDecoration(10));
//        rvTimeline.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.recyclerview_divider));
        tweetsAdapter = new TweetsAdapter(getContext(), this);
        tweetsAdapter.update(mPosts);
        rvTimeline.setAdapter(tweetsAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(PostModel post) {

    }

    @Override
    public void onReplyClick(PostModel post) {

    }

    @Override
    public void onRetweetClick(PostModel post) {

    }

    @Override
    public void onLikeClick(PostModel post) {

    }
}
