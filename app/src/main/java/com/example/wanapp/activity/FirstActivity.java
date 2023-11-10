package com.example.wanapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wanapp.R;
import com.example.wanapp.component.DaggerBookComponent;
import com.example.wanapp.info.Book;
import com.example.wanapp.module.BookModule;

import javax.inject.Inject;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "Dagger";

    @Inject
    Book book;

    @Inject
    Book book2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //DaggerBookComponent是由dagger-compiler通过APT自动生成的
        DaggerBookComponent.builder()
                .bookModule(new BookModule())
                .build().injectFirstActivity(this);

        //另个Book的hashCode不一样，说明Dagger2提供的Book默认是非单例的
        Log.d(TAG, "book: " + book.hashCode());
        Log.d(TAG, "book2: " + book2.hashCode());
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inject = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(inject);
            }
        });
    }


}