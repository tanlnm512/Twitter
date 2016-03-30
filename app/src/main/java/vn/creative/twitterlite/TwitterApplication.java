package vn.creative.twitterlite;

import android.content.Context;

import com.activeandroid.app.Application;

import vn.creative.twitterlite.service.TwitterService;

/**
 * Created by tanlnm on 3/30/2016.
 */
public class TwitterApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static TwitterService getService() {
        return (TwitterService) TwitterService.getInstance(TwitterService.class, TwitterApplication.mContext);
    }
}
