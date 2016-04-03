package vn.creative.twitterlite.view.timeline.home;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import vn.creative.twitterlite.TwitterApplication;
import vn.creative.twitterlite.service.IResultListener;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public class HomeTabInteractor implements IHomeTabInteractor {
    @Override
    public void getHomeTimeline(long sinceId, final IResultListener listener) {
        TwitterApplication.getService().getHomeTimeline(sinceId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onFail(errorResponse, throwable);
            }
        });
    }
}
