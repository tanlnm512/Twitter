package vn.creative.twitterlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterlite.R;

/**
 * Created by minhtan512 on 4/4/2016.
 */
public class PhotosHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.item_photo)
    ImageView ivPhoto;

    public PhotosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
