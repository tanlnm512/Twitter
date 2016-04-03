package vn.creative.twitterlite.view.timeline.home;

import java.util.List;

import vn.creative.twitterlite.model.PostModel;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public interface IHomeTabView {
    void onFetchTimelineSuccess(List<PostModel> posts);

    void onFetchTimelineFail();
}
