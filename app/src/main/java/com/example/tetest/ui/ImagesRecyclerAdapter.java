package com.example.tetest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tetest.R;

import java.util.List;

/**
 * Created by alexandrlyadinskii on 29.07.15.
 * All rights reserved©
 */
public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private List<String> mImageUrls;

    public ImagesRecyclerAdapter(List<String> imageUrls) {
        mImageUrls = imageUrls;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_view_holder, viewGroup, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder imageViewHolder, int i) {
        imageViewHolder.loadImage(mImageUrls.get(i));
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }
}
