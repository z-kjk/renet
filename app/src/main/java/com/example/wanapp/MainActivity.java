package com.example.wanapp;

import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit mretrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Android 4.0 之后不能在主线程中请求HTTP请求
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//        getJsondata();
//            }
//        }).start();
//        showImg();
//        //订阅,此时触发1步中subscribe方法的执行
//        observableByFrom.subscribe(observer);
        Intent inject = new Intent(MainActivity.this, TabActivity.class);
        startActivity(inject);
    }


    public void showImg()
    {
        Uri uri = Uri.parse("https://img-home.csdnimg.cn/images/20201124032511.png");
        ImageView img = findViewById(R.id.imageView);
        Glide.with(this)
                .load(uri)
                .into(img);
    }
}