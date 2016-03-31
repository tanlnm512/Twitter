package vn.creative.twitterlite.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.creative.twitterlite.R;

/**
 * Created by tanlnm on 3/31/2016.
 */
public class MentionTabFrg extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_mention, container, false);
        return view;
    }
}
