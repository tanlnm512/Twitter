package vn.creative.twitterlite.view.profile.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.TwitterApplication;
import vn.creative.twitterlite.adapter.FavoritesAdapter;
import vn.creative.twitterlite.adapter.TimelineAdapter;
import vn.creative.twitterlite.adapter.TweetsAdapter;
import vn.creative.twitterlite.common.VerticalSpaceItemDecoration;
import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.view.timeline.home.ITimelineActionListener;

/**
 * Created by tanlnm on 4/4/2016.
 */
public class FavoritesTabFrg extends Fragment implements ITimelineActionListener {
    @Bind(R.id.tab_favorites_rv_timeline)
    RecyclerView rvFavorites;

    private FavoritesAdapter favoritesAdapter;
    private static long nId;
    private long nCurId = 1;

    public static FavoritesTabFrg newInstance(long id) {
        nId = id;
        return  new FavoritesTabFrg();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_tab_favorites, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvFavorites.setLayoutManager(linearLayoutManager);
        rvFavorites.addItemDecoration(new VerticalSpaceItemDecoration(10));
        favoritesAdapter = new FavoritesAdapter(getContext(), this);
        rvFavorites.setAdapter(favoritesAdapter);

        TwitterApplication.getService().getFavorite(nCurId, nId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Type type = new TypeToken<List<PostModel>>() {
                }.getType();
                List<PostModel> posts = new Gson().fromJson(response.toString(), type);
                favoritesAdapter.update(posts);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("DEBUG", "load favorites fail!", throwable);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(PostModel post) {

    }

    @Override
    public void onReplyClick(PostModel post) {

    }

    @Override
    public void onRetweetClick(PostModel post) {

    }

    @Override
    public void onLikeClick(PostModel post) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
