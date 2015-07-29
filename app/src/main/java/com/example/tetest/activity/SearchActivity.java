package com.example.tetest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.example.tetest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.edit_search_string)
    EditText mEditSearchString;
    @Bind(R.id.button_scan)
    Button mButtonScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    @OnTextChanged(R.id.edit_search_string)
    public void searchStringChanged(CharSequence text) {
        mButtonScan.setEnabled(Patterns.WEB_URL.matcher(text).matches());
    }

    @OnClick(R.id.button_scan)
    public void scan() {
        String url = mEditSearchString.getText().toString();

        if (!url.contains("http")) {
            url = "http://" + url;
        }

        Intent intent = new Intent(SearchActivity.this, ImageListActivity.class);
        intent.putExtra(ImageListActivity.URI, url);
        startActivity(intent);
    }
}
