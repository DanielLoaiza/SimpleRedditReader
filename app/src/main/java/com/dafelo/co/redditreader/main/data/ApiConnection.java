package com.dafelo.co.redditreader.main.data;

import com.dafelo.co.redditreader.deserializer.RedditObjectJsonDeserializer;
import com.dafelo.co.redditreader.main.domain.RedditObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 22/10/16.
 */

public class ApiConnection {

    private static final String BASE_URL = "https://www.reddit.com/";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    public Retrofit createClient() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(RedditObject.class, new RedditObjectJsonDeserializer())
                //.registerTypeAdapter(Date.class, new RedditDateDeserializer())
                .create();

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request request = originalRequest.newBuilder()
                            .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                            .method(originalRequest.method(), originalRequest.body())
                            .build();

                    return chain.proceed(request);
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
        // se añade el adapter para indicar a retrofit que use rxJava y el converter para que haga uso Gson
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }
}
