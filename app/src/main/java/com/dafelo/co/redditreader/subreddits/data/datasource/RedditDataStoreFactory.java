package com.dafelo.co.redditreader.subreddits.data.datasource;


import com.dafelo.co.redditreader.main.data.ApiConnection;
import com.dafelo.co.redditreader.main.data.RedditClient;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by root on 25/11/16.
 */

public class RedditDataStoreFactory {

    @Inject
    public RedditDataStoreFactory() {
    }
    /**
     * Create {@link RedditDataStore} to retrieve data from the Cloud.
     */
    public RedditDataStore createCloudDataStore() {
        ApiConnection apiConnection = new ApiConnection();
        Retrofit retrofit = apiConnection.createClient();
        RedditClient redditService = retrofit.create(RedditClient.class);

        return new CloudRedditDataStore(redditService);
    }
}
