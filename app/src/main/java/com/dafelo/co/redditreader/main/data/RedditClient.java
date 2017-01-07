package com.dafelo.co.redditreader.main.data;

import com.dafelo.co.redditreader.main.domain.RedditObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 7/01/17.
 */

public interface RedditClient
{
    @GET("reddits.json")
    Observable<RedditObject> getRedditsData(@Query("after") String after, @Query("limit") int limit);
}
