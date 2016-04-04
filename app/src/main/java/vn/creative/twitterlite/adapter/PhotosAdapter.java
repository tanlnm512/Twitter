package vn.creative.twitterlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.creative.twitterlite.R;
import vn.creative.twitterlite.common.RoundedTransformation;

/**
 * Created by minhtan512 on 4/4/2016.
 */
public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> imgUrls;

    public PhotosAdapter(Context context, List<String> urls) {
        mContext = context;
        imgUrls = urls;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View coverView = inflater.inflate(R.layout.item_photo, viewGroup, false);
        viewHolder = new PhotosHolder(coverView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PhotosHolder photosHolder = (PhotosHolder) holder;
        String imgUrl = imgUrls.get(position);
        Picasso.with(mContext)
                .load(imgUrl)
                .transform(new RoundedTransformation(10, 0))
                .placeholder(R.drawable.photo_placeholder)
                .tag(mContext)
                .fit()
                .into(photosHolder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }
}
