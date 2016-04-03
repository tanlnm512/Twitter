package vn.creative.twitterlite.view.timeline.home;

import vn.creative.twitterlite.model.PostModel;

/**
 * Created by tanlnm on 3/28/2016.
 */
public interface ITimelineActionListener {
    void onItemClick(PostModel post);

    void onReplyClick(PostModel post);

    void onRetweetClick(PostModel post);

    void onLikeClick(PostModel post);
}
