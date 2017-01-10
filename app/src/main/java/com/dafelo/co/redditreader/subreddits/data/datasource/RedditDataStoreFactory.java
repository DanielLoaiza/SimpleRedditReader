package com.dafelo.co.redditreader.subreddits.data.datasource;


import android.content.Context;

import com.dafelo.co.redditreader.main.data.ApiConnection;
import com.dafelo.co.redditreader.main.data.RedditClient;
import com.dafelo.co.redditreader.main.domain.schedulers.ObserveOn;
import com.dafelo.co.redditreader.subreddits.data.datasource.local.LocalRedditDataStore;
import com.dafelo.co.redditreader.subreddits.data.datasource.remote.CloudRedditDataStore;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by root on 25/11/16.
 */

public class RedditDataStoreFactory {

    Context context;
    ObserveOn observeOn;
    LocalRedditDataStore localRedditDataStore;
    @Inject
    public RedditDataStoreFactory(Context context, ObserveOn observeOn, LocalRedditDataStore localRedditDataStore) {
        this.context = context;
        this.observeOn = observeOn;
        this.localRedditDataStore = localRedditDataStore;

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

    /**
     * Create {@link RedditDataStore} to retrieve data from the sql lite database.
     */
    public RedditDataStore createLocalDataStore() {

        return localRedditDataStore;
    }
}
