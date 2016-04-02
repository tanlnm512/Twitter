package vn.creative.twitterlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.creative.twitterlite.model.MentionModel;
import vn.creative.twitterlite.view.home.ITimelineActionListener;
import vn.creative.twitterlite.view.mention.IMentionActionListener;

/**
 * Created by TanLe on 4/2/16.
 */
public class MentionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private IMentionActionListener mListener;
    private List<MentionModel> mentions;

    public MentionAdapter(Context context, IMentionActionListener listener) {
        mContext = context;
        mentions = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mentions.size();
    }
}
