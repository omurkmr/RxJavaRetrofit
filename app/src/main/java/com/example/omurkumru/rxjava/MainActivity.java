package com.example.omurkumru.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.omurkumru.rxjava.Adapter.PostAdapter;
import com.example.omurkumru.rxjava.Model.Post;
import com.example.omurkumru.rxjava.Retrofit.RetrofitClient;
import com.example.omurkumru.rxjava.Retrofit.TestAPI;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    TestAPI testAPI;
    RecyclerView testApiRV;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        testAPI = retrofit.create(TestAPI.class);

        testApiRV = findViewById(R.id.postList_recyclerView);
        testApiRV.setHasFixedSize(true);
        testApiRV.setLayoutManager(new LinearLayoutManager(this));
        
        fetchData();


    }

    private void fetchData() {
        compositeDisposable.add(testAPI.getPostList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                displayData(posts);
            }
        }));
    }

    private void displayData(List<Post> posts) {
        PostAdapter postAdapter = new PostAdapter(this,posts);
        testApiRV.setAdapter(postAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
