package vn.creative.twitterlite.view.home;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import vn.creative.twitterlite.TwitterApplication;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public class HomeTabInteractor implements IHomeTabInteractor {
    @Override
    public void getHomeTimeline(long id, final IFetchTimelineListener listener) {
        TwitterApplication.getService().getHomeTimeline(id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                listener.onSuccess(response);
//                try {
//                    for(int i = 0; i < response.length(); i++) {
//                        System.out.println(">>> " + response.getJSONObject(i).toString());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onFail(errorResponse, throwable);
            }
        });
    }
}
