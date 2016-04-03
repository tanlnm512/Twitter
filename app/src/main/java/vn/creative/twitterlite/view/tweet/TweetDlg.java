package vn.creative.twitterlite.view.tweet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.TwitterApplication;
import vn.creative.twitterlite.common.RoundedTransformation;
import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.model.UserModel;
import vn.creative.twitterlite.view.timeline.home.HomeTabFrg;

/**
 * Created by tanlnm on 3/28/2016.
 */
public class TweetDlg extends DialogFragment {
    @Bind(R.id.dlg_tv_screen_name)
    TextView tvScreenName;

    @Bind(R.id.dlg_tv_name)
    TextView tvName;

    @Bind(R.id.dlg_tv_char_count)
    TextView tvCharCount;

    @Bind(R.id.dlg_et_tweet)
    EditText etTweet;

    @Bind(R.id.dlg_iv_avatar)
    ImageView ivAvatar;

    @Bind(R.id.dlg_btn_tweet)
    Button btnTweet;

    private PostModel post;
    private boolean tweetFlag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tweet, null);
        ButterKnife.bind(this, view);
        setCancelable(false);
        btnTweet.setEnabled(false);
        tweetFlag = false;

        view.findViewById(R.id.dlg_iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        etTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charCount = 140 - s.length();
                tvCharCount.setText(String.valueOf(charCount));

                if (charCount > 140) {
                    btnTweet.setEnabled(false);
                } else {
                    btnTweet.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (getArguments() != null) {
            post = (PostModel) getArguments().get("post");
            Picasso.with(getContext())
                    .load(post.getUser().getAvatar())
                    .transform(new RoundedTransformation(20, 0))
                    .resize(150, 150)
                    .centerCrop()
                    .tag(getContext())
                    .into(ivAvatar);

            tvName.setText(post.getUser().getName());
            tvScreenName.setText("@" + post.getUser().getScreenName());

            etTweet.setText("@" + post.getUser().getScreenName());

        } else {
            TwitterApplication.getService().getUserInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    UserModel user = new Gson().fromJson(response.toString(), UserModel.class);
                    if(user != null) {
                        Picasso.with(getContext())
                                .load(user.getAvatar())
                                .transform(new RoundedTransformation(20, 0))
                                .resize(150, 150)
                                .centerCrop()
                                .tag(getContext())
                                .into(ivAvatar);

                        tvName.setText(user.getName());
                        tvScreenName.setText("@" + user.getScreenName());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.e("DEBUG", "get user info fail!", throwable);
                }
            });
        }

        return view;
    }

    @OnClick(R.id.dlg_iv_close)
    void close() {
        dismiss();
    }

    @OnClick(R.id.dlg_btn_tweet)
    void tweet() {
        if (post != null) {
            TwitterApplication.getService().replyTweet(etTweet.getText().toString(), post.getId(), new JsonHttpResponseHandler());

        } else {
            TwitterApplication.getService().tweet(etTweet.getText().toString(), new JsonHttpResponseHandler());
        }

        tweetFlag = true;
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if(tweetFlag) {
            Fragment frg = getFragmentManager().findFragmentByTag("TimelineFrg");
            if (frg instanceof HomeTabFrg) {
                ((DialogInterface.OnDismissListener) frg).onDismiss(dialog);
            }
        }
    }
}
