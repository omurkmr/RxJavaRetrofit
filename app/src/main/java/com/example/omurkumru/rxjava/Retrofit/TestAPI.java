package com.example.omurkumru.rxjava.Retrofit;

import com.example.omurkumru.rxjava.Model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TestAPI {

    @GET("posts")
    Observable<List<Post>> getPostList();
}
