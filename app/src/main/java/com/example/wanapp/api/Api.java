package com.example.wanapp.api;

import com.example.wanapp.info.Info;
import com.example.wanapp.info.chapters;
import com.example.wanapp.result.Data;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/api/rand.music")
    Call<Data<Info>> getJsonData(@Query("sort")String sort,@Query("format")String formate);


    //公众号
    @GET("wxarticle/chapters/json")
    Observable<Data<List<chapters>>> getChapData();
}
