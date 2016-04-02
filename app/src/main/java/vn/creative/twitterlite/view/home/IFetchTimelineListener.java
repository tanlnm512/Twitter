package vn.creative.twitterlite.view.home;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public interface IFetchTimelineListener {
    void onSuccess(JSONArray response);

    void onFail(JSONObject error, Throwable throwable);
}
