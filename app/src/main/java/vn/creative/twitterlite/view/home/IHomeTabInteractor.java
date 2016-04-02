package vn.creative.twitterlite.view.home;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public interface IHomeTabInteractor {
    void getHomeTimeline(long id, IFetchTimelineListener listener);
}
