package vn.creative.twitterlite.view.home;

import vn.creative.twitterlite.service.IResultListener;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public interface IHomeTabInteractor {
    void getHomeTimeline(long id, long maxId, IResultListener listener);
}
