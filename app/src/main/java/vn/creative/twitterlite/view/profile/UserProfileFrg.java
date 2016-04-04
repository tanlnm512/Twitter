package vn.creative.twitterlite.view.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.TwitterApplication;
import vn.creative.twitterlite.adapter.ProfilePagerAdapter;
import vn.creative.twitterlite.common.RoundedTransformation;
import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.model.UserModel;

/**
 * Created by minhtan512 on 4/3/2016.
 */
public class UserProfileFrg extends Fragment {
    @Bind(R.id.profile_tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.profile_pager)
    ViewPager viewPager;

    @Bind(R.id.profile_iv_banner)
    ImageView ivProfileBanner;

    @Bind(R.id.profile_iv_avatar)
    ImageView ivProfileAvatar;

    @Bind(R.id.profile_btn_follow)
    Button btnFollow;

    @Bind(R.id.profile_tv_following)
    TextView tvFollowing;

    @Bind(R.id.profile_tv_follower)
    TextView tvFollower;

    @Bind(R.id.profile_tv_intro)
    TextView tvIntro;

    @Bind(R.id.profile_tv_name)
    TextView tvName;

    @Bind(R.id.profile_tv_screen_name)
    TextView tvScreenName;

    private long nUserId = -1;
    private long nCurId = 1;

    private ProfilePagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        setHasOptionsMenu(true);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            nUserId = getArguments().getLong("id");
        }

        tabLayout.addTab(tabLayout.newTab().setText("Tweets"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Favorites"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (nUserId == -1) {
            loadProfile();
            loadTimeline();

        } else {
            loadUserProfile();
            loadUserTimeline();
        }

        return view;
    }

    private void loadProfile() {
        TwitterApplication.getService().getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                UserModel user = new Gson().fromJson(response.toString(), UserModel.class);
                if (user != null) {
                    Picasso.with(getContext())
                            .load(user.getAvatar())
                            .transform(new RoundedTransformation(5, 0))
                            .resize(100, 100)
                            .placeholder(R.drawable.avatar_placeholder)
                            .tag(getContext())
                            .centerCrop()
                            .into(ivProfileAvatar);

                    Picasso.with(getContext())
                            .load(user.getProfileBannerUrl())
                            .placeholder(R.drawable.photo_placeholder)
                            .tag(getContext())
                            .fit()
                            .into(ivProfileBanner);

                    btnFollow.setVisibility(View.GONE);
                    tvFollowing.setText(user.getFriendsCount() + " FOLLOWING");
                    tvFollower.setText(user.getFollowersCount() + " FOLLOWER");
                    tvName.setText(user.getName());
                    tvScreenName.setText("@" + user.getScreenName());
                    tvIntro.setText(user.getDescription());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("DEBUG", "Load profile fail!", throwable);
            }
        });
    }

    private void loadUserProfile() {
        TwitterApplication.getService().getUserInfo(nUserId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                UserModel user = new Gson().fromJson(response.toString(), UserModel.class);
                if (user != null) {
                    Picasso.with(getContext())
                            .load(user.getAvatar())
                            .transform(new RoundedTransformation(5, 0))
                            .resize(100, 100)
                            .placeholder(R.drawable.avatar_placeholder)
                            .tag(getContext())
                            .centerCrop()
                            .into(ivProfileAvatar);

                    Picasso.with(getContext())
                            .load(user.getProfileBannerUrl())
                            .placeholder(R.drawable.photo_placeholder)
                            .tag(getContext())
                            .fit()
                            .into(ivProfileBanner);

                    btnFollow.setText(user.isFollowing() ? "Unfollow" : "Follow");
                    tvFollowing.setText(user.getFriendsCount() + " FOLLOWING");
                    tvFollower.setText(user.getFollowersCount() + " FOLLOWER");
                    tvName.setText(user.getName());
                    tvScreenName.setText("@" + user.getScreenName());
                    tvIntro.setText(user.getDescription());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("DEBUG", "Load profile fail!", throwable);
            }
        });
    }

    private void loadTimeline() {
        TwitterApplication.getService().getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                final UserModel user = new Gson().fromJson(response.toString(), UserModel.class);
                if(user != null) {
                    TwitterApplication.getService().getUserTimeline(nCurId, user.getId(), new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            Type type = new TypeToken<List<PostModel>>() {
                            }.getType();
                            List<PostModel> posts = new Gson().fromJson(response.toString(), type);
                            adapter = new ProfilePagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), posts, user.getId());
                            viewPager.setAdapter(adapter);
                        }
                    });
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("DEBUG", "load timeline fail!", throwable);
            }
        });
    }

    private void loadUserTimeline() {
        TwitterApplication.getService().getUserTimeline(nCurId, nUserId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Type type = new TypeToken<List<PostModel>>() {
                }.getType();
                List<PostModel> posts = new Gson().fromJson(response.toString(), type);
                adapter = new ProfilePagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), posts, nUserId);
                viewPager.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("DEBUG", "load timeline fail!", throwable);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}