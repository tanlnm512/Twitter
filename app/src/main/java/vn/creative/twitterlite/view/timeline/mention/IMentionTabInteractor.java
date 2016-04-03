package vn.creative.twitterlite.view.timeline.mention;

import vn.creative.twitterlite.service.IResultListener;

/**
 * Created by TanLe on 4/2/16.
 */
public interface IMentionTabInteractor {
    void getMention(long id, IResultListener resultListener);
}
