package com.example.wanapp.module;

import com.example.wanapp.info.Book;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BookModule {
    @Singleton
    @Provides
    public Book provideBook(){
        return new Book();
    }
}
