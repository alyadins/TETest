package com.example.tetest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.tetest.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexandrlyadinskii on 29.07.15.
 * All rights reserved©
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.image_view)
    ImageView mImageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
//        R.layout.image_view_holder
    }

    public void loadImage(String url) {
        Picasso.with(itemView.getContext()).load(url).into(mImageView);
    }
}
