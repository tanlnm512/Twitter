package vn.creative.twitterlite.view.profile.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.creative.twitterlite.R;
import vn.creative.twitterlite.model.PostModel;

/**
 * Created by tanlnm on 4/4/2016.
 */
public class FavoritesTabFrg extends Fragment {
    private static List<PostModel> mPosts;

    public static FavoritesTabFrg newInstance(List<PostModel> posts) {
        mPosts = posts;
        return  new FavoritesTabFrg();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        long id = getArguments().getLong("id");

        View view = inflater.inflate(R.layout.fragment_tab_favorites, container, false);
        return view;
    }
}
