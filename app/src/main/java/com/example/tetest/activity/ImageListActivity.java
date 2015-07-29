package com.example.tetest.activity;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.tetest.R;
import com.example.tetest.ui.ImagesRecyclerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexandrlyadinskii on 29.07.15.
 * All rights reserved©
 */
public class ImageListActivity extends AppCompatActivity {

    public static final String URI = "url";
    private static final int COLUMNS_COUNT = 2;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private URI mUri;

    private List<String> mImageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS_COUNT));

        initData();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mUri.toString());
        }
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initData() {
        String uri = getIntent().getStringExtra(URI);

        if (uri == null || uri.isEmpty()) {
            throw new IllegalStateException("url must be set");
        }

        mUri = java.net.URI.create(uri);
        mImageUrls = new ArrayList<>();
        fetchPage();
    }

    private void fetchPage() {
        new Thread(() -> {
            try {
                mImageUrls.clear();
                Document doc = Jsoup.connect(mUri.toString()).get();
                for (Element img : doc.select("img")) {
                    String link = img.attr("abs:src");
                    mImageUrls.add(link);
                }

                if (mImageUrls.isEmpty()) {
                    showErrorToast(R.string.images_not_found);
                    return;
                }

                initRecycler();
            } catch (IOException e) {
                showErrorToast(R.string.error_loading_page);
            }
        }).start();
    }

    private void initRecycler() {
        runOnUiThread(() -> mRecyclerView.setAdapter(new ImagesRecyclerAdapter(mImageUrls)));
    }

    private void showErrorToast(@StringRes int resId) {
        runOnUiThread(() -> {
            Toast.makeText(ImageListActivity.this, resId, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
