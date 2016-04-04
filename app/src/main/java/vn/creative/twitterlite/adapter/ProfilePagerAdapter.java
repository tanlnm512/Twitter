package vn.creative.twitterlite.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.creative.twitterlite.view.profile.favorites.FavoritesTabFrg;
import vn.creative.twitterlite.view.profile.photos.PhotosTabFrg;
import vn.creative.twitterlite.view.profile.tweets.TweetsTabFrg;

/**
 * Created by tanlnm on 4/4/2016.
 */
public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    private int nTabCount;
    private long nId;

    public ProfilePagerAdapter(FragmentManager fm, int tabCount, long id) {
        super(fm);
        nTabCount = tabCount;
        nId = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TweetsTabFrg.newInstance(nId);

            case 1:
                return PhotosTabFrg.newInstance(nId);

            case 2:
                return FavoritesTabFrg.newInstance(nId);

            default:
                return TweetsTabFrg.newInstance(nId);
        }
    }

    @Override
    public int getCount() {
        return nTabCount;
    }
}
