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
 * Created by dafelo on 07/10/17.
 */

/**
 * Class that handles the connection to the api
 */
public class ApiConnection {

    private static final String BASE_URL = "https://www.reddit.com/";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    /**
     *
     * @return Retrofit, an instance of Retrofit with all the configuration
     */
    public Retrofit createClient() {
        // create a Gson Instance
        Gson gson = new GsonBuilder()
                // set naming policy to lower case with underscores to tell
                // gson that all the api fields come with this policy and avoid
                // the mapping on the class
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                // use a custom deserializer because the reddit api has different formats
                // depending on the kind in the response, this way app doesnt break
                .registerTypeAdapter(RedditObject.class, new RedditObjectJsonDeserializer())
                .create();

        // create okHttpClient and add custom header
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request request = originalRequest.newBuilder()
                            .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                            .method(originalRequest.method(), originalRequest.body())
                            .build();

                    return chain.proceed(request);
                })
                // set read and connection timeout to 60 and 20 seconds
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
        // add adapter to tell retrofit to use RxJava by default , and the custom gson converter
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }
}
