package vn.creative.twitterlite.view.profile.tweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.creative.twitterlite.R;

/**
 * Created by tanlnm on 4/4/2016.
 */
public class TweetsTabFrg extends Fragment {
    public static TweetsTabFrg newInstance(long id) {
        TweetsTabFrg fragment = new TweetsTabFrg();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null) {
            return null;
        }

        long id = getArguments().getLong("id");

        View view = inflater.inflate(R.layout.fragment_tab_tweets, container, false);
        return view;
    }
}
