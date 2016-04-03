package vn.creative.twitterlite.view.timeline.home;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.service.IResultListener;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public class HomeTabPresenter implements IHomeTabPresenter, IResultListener {
    private IHomeTabView homeTabView;
    private HomeTabInteractor homeTabInteractor;

    public HomeTabPresenter(IHomeTabView homeTabView) {
        this.homeTabView = homeTabView;
        homeTabInteractor = new HomeTabInteractor();
    }

    @Override
    public void fetchTimeline(long sinceId) {
        homeTabInteractor.getHomeTimeline(sinceId, this);
    }

    @Override
    public void onSuccess(JSONArray response) {
        Type type = new TypeToken<List<PostModel>>() {
        }.getType();
        List<PostModel> posts = new Gson().fromJson(response.toString(), type);
//        for (PostModel post : posts) {
//            DBPost dbPost = new DBPost(new Gson().toJson(post));
//            dbPost.save();
//        }
        homeTabView.onFetchTimelineSuccess(posts);
    }

    @Override
    public void onFail(JSONObject error, Throwable throwable) {
        homeTabView.onFetchTimelineFail();
        Log.e("DEBUG", "fetch timeline fail!", throwable);
    }
}
