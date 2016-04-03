package vn.creative.twitterlite.view.timeline.mention;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import vn.creative.twitterlite.TwitterApplication;
import vn.creative.twitterlite.service.IResultListener;

/**
 * Created by TanLe on 4/2/16.
 */
public class MentionTabInteractor implements IMentionTabInteractor {
    @Override
    public void getMention(long id, final IResultListener listener) {
        TwitterApplication.getService().getMention(id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onFail(errorResponse, throwable);
                Log.e("DEBUG", "fetch mention fail!", throwable);
            }
        });
    }
}
