package vn.creative.twitterlite.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by minhtan512 on 4/2/2016.
 */
public class CommonUtils {
    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private static final long MINUTE = 60;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;
    private static final long WEEK = 7 * DAY;

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            long gapTime = (System.currentTimeMillis() - dateMillis) / 1000L;
            if (gapTime < MINUTE) {
                relativeDate = "few secs";

            } else if (gapTime < HOUR) {
                relativeDate = gapTime / MINUTE + "min ago";

            } else if (gapTime < DAY) {
                relativeDate = gapTime / HOUR + "h ago";

            } else if (gapTime < WEEK) {
                relativeDate = gapTime / DAY + "d ago";

            } else {
                relativeDate = gapTime / WEEK + "w ago";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
