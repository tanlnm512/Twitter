package vn.creative.twitterlite.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.view.profile.favorites.FavoritesTabFrg;
import vn.creative.twitterlite.view.profile.photos.PhotosTabFrg;
import vn.creative.twitterlite.view.profile.tweets.TweetsTabFrg;

/**
 * Created by tanlnm on 4/4/2016.
 */
public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    private int nTabCount;
    private List<PostModel> mPosts;
    private long nId;

    public ProfilePagerAdapter(FragmentManager fm, int tabCount, List<PostModel> posts, long id) {
        super(fm);
        nTabCount = tabCount;
        mPosts = posts;
        nId = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TweetsTabFrg.newInstance(mPosts);

            case 1:
                return PhotosTabFrg.newInstance(mPosts);

            case 2:
                return FavoritesTabFrg.newInstance(nId);

            default:
                return TweetsTabFrg.newInstance(mPosts);
        }
    }

    @Override
    public int getCount() {
        return nTabCount;
    }
}
