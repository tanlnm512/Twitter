package vn.creative.twitterlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import vn.creative.twitterlite.R;
import vn.creative.twitterlite.common.CommonUtils;
import vn.creative.twitterlite.common.RoundedTransformation;
import vn.creative.twitterlite.model.PostModel;
import vn.creative.twitterlite.view.timeline.home.ITimelineActionListener;

/**
 * Created by minhtan512 on 4/4/2016.
 */
public class TweetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<PostModel> posts;
    private ITimelineActionListener mListener;

    public TweetsAdapter(Context context, ITimelineActionListener listener) {
        mContext = context;
        posts = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View coverView = inflater.inflate(R.layout.item_post, viewGroup, false);
        viewHolder = new PostHolder(coverView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostHolder postHolder = (PostHolder) holder;

        final PostModel post = posts.get(position);
        Picasso.with(mContext)
                .load(post.getUser().getAvatar())
                .transform(new RoundedTransformation(20, 0))
                .resize(150, 150)
                .centerCrop()
                .tag(mContext)
                .into(postHolder.ivAvatar);
        postHolder.tvScreenName.setText("@" + post.getUser().getScreenName());
        postHolder.tvName.setText(post.getUser().getName());
        postHolder.tvTime.setText(CommonUtils.getRelativeTimeAgo(post.getCreatedAt()));
        postHolder.tvRetweetCount.setText(String.valueOf(post.getRetweetCount()));
        postHolder.tvLikeCount.setText(String.valueOf(post.getFavoriteCount()));

        if (!TextUtils.isEmpty(post.getText())) {
            postHolder.tvPost.setVisibility(View.VISIBLE);
            postHolder.tvPost.setText(post.getText());
        } else {
            postHolder.tvPost.setVisibility(View.GONE);
        }

        if (post.getEntities().getMedia() != null && post.getEntities().getMedia().size() > 0) {
            postHolder.ivPhoto.setVisibility(View.VISIBLE);
            Picasso.with(mContext)
                    .load(post.getEntities().getMedia().get(0).getUrl())
                    .transform(new RoundedTransformation(10, 0))
                    .placeholder(R.drawable.photo_placeholder)
                    .tag(mContext)
                    .fit()
                    .into(postHolder.ivPhoto);

        } else {
            postHolder.ivPhoto.setVisibility(View.GONE);
        }

        postHolder.tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(post);
            }
        });

        postHolder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(post);
            }
        });

        postHolder.ivReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReplyClick(post);
            }
        });

        postHolder.ivRetweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReplyClick(post);
            }
        });

        postHolder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReplyClick(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void update(List<PostModel> postList) {
        posts.clear();
        posts.addAll(postList);
        notifyDataSetChanged();
    }

    public void updateMore(List<PostModel> postList) {
        int curSize = getItemCount();
        posts.addAll(postList);
        notifyItemRangeInserted(curSize, posts.size() - 1);
    }
}
