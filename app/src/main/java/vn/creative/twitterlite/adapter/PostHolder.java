package vn.creative.twitterlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class PostHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.iv_avatar) ImageView ivAvatar;
    @Bind(R.id.iv_photo) ImageView ivPhoto;
    @Bind(R.id.iv_reply) ImageView ivReply;
    @Bind(R.id.iv_reply_on) ImageView ivReplyOn;
    @Bind(R.id.iv_retweet) ImageView ivRetweet;
    @Bind(R.id.iv_retweet_on) ImageView ivRetweetOn;
    @Bind(R.id.iv_like) ImageView ivLike;
    @Bind(R.id.iv_like_on) ImageView ivLikeOn;
    @Bind(R.id.tv_screen_name) TextView tvScreenName;
    @Bind(R.id.tv_name) TextView tvName;
    @Bind(R.id.tv_time) TextView tvTime;
    @Bind(R.id.tv_post) TextView tvPost;
    @Bind(R.id.tv_retweet_count) TextView tvRetweetCount;
    @Bind(R.id.tv_like_count) TextView tvLikeCount;

    public PostHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
