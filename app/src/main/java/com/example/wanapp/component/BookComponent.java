package com.example.wanapp.component;

import com.example.wanapp.activity.FirstActivity;
import com.example.wanapp.activity.SecondActivity;
import com.example.wanapp.module.BookModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BookModule.class)
public interface BookComponent {    //接口
    void injectFirstActivity(FirstActivity activity);

    void injectSecondActivity(SecondActivity activity);
}
