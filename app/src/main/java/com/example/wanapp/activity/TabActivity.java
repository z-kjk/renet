package com.example.wanapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wanapp.MainActivity;
import com.example.wanapp.R;
import com.example.wanapp.adapter.TabAdapter;
import com.example.wanapp.api.Api;
import com.example.wanapp.fragment.TabFragment;
import com.example.wanapp.info.chapters;
import com.example.wanapp.result.Data;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabActivity extends AppCompatActivity {

    private Retrofit mretrofit;
    private TabLayout myTab;
    private ViewPager2 myPager2;
    private ViewPager viewPager;
    private SlidingTabLayout slide;

    List<String> titles=new ArrayList<>();  //标题

    List<Fragment> fragments=new ArrayList<>(); //fragement
    List<chapters> datas = new ArrayList<>();   //获得的块状数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        myTab = findViewById(R.id.my_tab);
        myPager2 = findViewById(R.id.my_pager2);
//        slide = findViewById(R.id.slide); //viewpager
//        viewPager = findViewById(R.id.viewPage);

        initRetro();


    }

    public void initRetro()
    {
        mretrofit= new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        getJsondata();
    }
    public void getJsondata()   //获得返回的数据
    {
        Api api = mretrofit.create(Api.class);
//        Observable<Data<List<chapters>>> call = api.getChapData();
        api.getChapData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data<List<chapters>>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Data<List<chapters>> listData) {
                if (listData == null) return;
                datas = listData.getData();
                if (datas == null) return;
                Log.e("Main","返回的数据：" + "\n\n" + datas.size());
//                for (int i =0;i<datas.size();i++)
//                {
//                    titles.add(datas.get(i).getName());
//                    Log.i("Tab",titles.get(i));
//                }
                initTitle(datas);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                showTitle();
                adapterData();
            }
        });
    }

    public void initTitle(List<chapters> datas) //初始化标题列表
    {
        int size = datas.size();
        for (int i =0;i<size;i++)
        {
            titles.add(datas.get(i).getName());
        }
    }
    private void showTitle()    //显示列表
    {
        Log.e("Tab","显示执行");
        for(int i =0;i<datas.size();i++)
        {
            Log.i("Tab",titles.get(i));
        }
    }
    private void adapterData()  //适配数据
    {
        //添加Fragment进去
        fragments.add(new TabFragment());
        for(int i=0;i<13;i++)
        {
            fragments.add(new Fragment());
        }

        //实例化适配器
        TabAdapter myAdapter=new TabAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        //设置适配器
        myPager2.setAdapter(myAdapter);
        //TabLayout和Viewpager2进行关联
        new TabLayoutMediator(myTab, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));  //设置标题
            }
        }).attach();
//这是和viewpager配置想调用那个滚动效果（应该）
//        PagerAdapter myAdapter=new PagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
//        //设置适配器
//        viewPager.setAdapter(myAdapter);
//        slide.setViewPager(viewPager, titles.toArray(new String[0]));
    }
}