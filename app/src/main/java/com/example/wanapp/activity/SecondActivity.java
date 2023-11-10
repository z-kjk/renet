package com.example.wanapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.wanapp.R;
import com.example.wanapp.component.DaggerBookComponent;
import com.example.wanapp.info.Book;
import com.example.wanapp.module.BookModule;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Dagger";

    @Inject
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DaggerBookComponent.builder()
                .bookModule(new BookModule())
                .build().injectSecondActivity(this);
        Log.d(TAG, "book: " + book.hashCode());
    }
}