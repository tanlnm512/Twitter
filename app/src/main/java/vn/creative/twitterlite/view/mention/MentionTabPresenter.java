package vn.creative.twitterlite.view.mention;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

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
        mentionTabView.onFetchMentionSuccess();
    }

    @Override
    public void onFail(JSONObject error, Throwable throwable) {
        mentionTabView.onFetchMentionFail();
    }
}
