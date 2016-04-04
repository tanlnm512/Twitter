package vn.creative.twitterlite.view.timeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.adapter.TimelinePagerAdapter;
import vn.creative.twitterlite.view.profile.UserProfileFrg;
import vn.creative.twitterlite.view.tweet.TweetDlg;

/**
 * Created by minhtan512 on 4/3/2016.
 */
public class TimelineFrg extends Fragment {
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager viewPager;

    private TimelinePagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.drawable.twitter_white);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.show();
        }

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Mention"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new TimelinePagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
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

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.menu_tab, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tweet:
                TweetDlg dlg = new TweetDlg();
                dlg.show(getFragmentManager(), "TweetDlg");
                break;

            case R.id.menu_message:
                Toast.makeText(getContext(), "Message!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_profile:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new UserProfileFrg())
                        .addToBackStack("UserProfileFrg").commit();
                Toast.makeText(getContext(), "Profile!", Toast.LENGTH_SHORT).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
