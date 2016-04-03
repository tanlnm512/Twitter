package vn.creative.twitterlite.view.timeline.mention;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.service.IResultListener;

/**
 * Created by TanLe on 4/2/16.
 */
public class MentionTabPresenter implements IMentionTabPresenter, IResultListener {
    private IMentionTabView mentionTabView;
    private MentionTabInteractor mentionTabInteractor;

    public MentionTabPresenter(IMentionTabView mentionTabView){
        this.mentionTabView = mentionTabView;
        mentionTabInteractor = new MentionTabInteractor();
    }

    @Override
    public void fetchMention(long id) {
        mentionTabInteractor.getMention(id, this);
    }

    @Override
    public void onSuccess(JSONArray response) {
        Type type = new TypeToken<List<PostModel>>() {
        }.getType();
        List<PostModel> posts = new Gson().fromJson(response.toString(), type);
        mentionTabView.onFetchMentionSuccess(posts);
    }

    @Override
    public void onFail(JSONObject error, Throwable throwable) {
        mentionTabView.onFetchMentionFail();
    }
}
