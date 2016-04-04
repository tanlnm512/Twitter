package vn.creative.twitterlite.view.profile.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.adapter.PhotosAdapter;
import vn.creative.twitterlite.model.PostModel;

/**
 * Created by tanlnm on 4/4/2016.
 */
public class PhotosTabFrg extends Fragment {
    @Bind(R.id.tab_photos_rv_timeline)
    RecyclerView rvPhoto;

    private static List<PostModel> mPosts;
    private PhotosAdapter photosAdapter;

    public static PhotosTabFrg newInstance(List<PostModel> posts) {
        mPosts = posts;
        return new PhotosTabFrg();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_tab_photos, container, false);
        ButterKnife.bind(this, view);

        List<String> imgUrls = new ArrayList<>();
        for (PostModel post : mPosts) {
            if (post.getEntities() != null && post.getEntities().getMedia() != null && !post.getEntities().getMedia().isEmpty()) {
                imgUrls.add(post.getEntities().getMedia().get(0).getUrl());
            }
        }

        rvPhoto.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        photosAdapter = new PhotosAdapter(getContext(), imgUrls);
        rvPhoto.setAdapter(photosAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
