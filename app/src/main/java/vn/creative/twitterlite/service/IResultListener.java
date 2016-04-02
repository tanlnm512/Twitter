package vn.creative.twitterlite.service;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by TanLe on 4/2/16.
 */
public interface IResultListener {
    void onSuccess(JSONArray response);

    void onFail(JSONObject error, Throwable throwable);
}
