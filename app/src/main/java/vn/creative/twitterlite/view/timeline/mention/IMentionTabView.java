package vn.creative.twitterlite.view.timeline.mention;

import java.util.List;

import vn.creative.twitterlite.model.PostModel;

/**
 * Created by TanLe on 4/2/16.
 */
public interface IMentionTabView {
    void onFetchMentionSuccess(List<PostModel> posts);

    void onFetchMentionFail();
}
