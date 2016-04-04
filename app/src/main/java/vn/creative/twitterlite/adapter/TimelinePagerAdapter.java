package vn.creative.twitterlite.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.creative.twitterlite.view.timeline.home.HomeTabFrg;
import vn.creative.twitterlite.view.timeline.mention.MentionTabFrg;

/**
 * Created by tanlnm on 3/31/2016.
 */
public class TimelinePagerAdapter extends FragmentStatePagerAdapter {
    private int nTabCount;

    public TimelinePagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        nTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeTabFrg();

            case 1:
                return new MentionTabFrg();

            default:
                return new HomeTabFrg();
        }
    }

    @Override
    public int getCount() {
        return nTabCount;
    }
}
