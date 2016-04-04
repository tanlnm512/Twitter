package vn.creative.twitterlite.view.profile.photos;

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
public class PhotosTabFrg extends Fragment{
    public static PhotosTabFrg newInstance(long id) {
        PhotosTabFrg fragment = new PhotosTabFrg();
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

        View view = inflater.inflate(R.layout.fragment_tab_photos, container, false);
        return view;
    }
}
