package vn.creative.twitterlite.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import vn.creative.twitterlite.R;
import vn.creative.twitterlite.view.timeline.TimelineFrg;

/**
 * Created by tanlnm on 3/30/2016.
 */
public class TwitterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new TimelineFrg(), "TimelineFrg")
                .commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
