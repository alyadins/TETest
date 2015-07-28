package com.example.tetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.view.OnClickEvent;
import rx.android.view.ViewObservable;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;

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

    @Override
    protected void onStart() {
        super.onStart();

        Observable<Boolean> urlValid = WidgetObservable.text(mEditSearchString)
                .map(OnTextChangeEvent::text)
                .map(s -> Patterns.WEB_URL.matcher(s).matches());

        urlValid.distinctUntilChanged()
                .startWith(false)
                .subscribe(mButtonScan::setEnabled);

        Observable<View> buttonClick = ViewObservable.clicks(mButtonScan, false)
                .map(OnClickEvent::view);

        buttonClick.subscribe(view -> scan(mEditSearchString.getText().toString()));
    }

    private void scan(String s) {
    }
}
