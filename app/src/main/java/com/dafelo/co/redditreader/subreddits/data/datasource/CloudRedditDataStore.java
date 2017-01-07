package com.dafelo.co.redditreader.subreddits.data.datasource;


import com.dafelo.co.redditreader.main.data.RedditClient;
import com.dafelo.co.redditreader.main.domain.RedditObject;

import rx.Observable;

/**
 * Created by root on 25/11/16.
 */

public class CloudRedditDataStore implements RedditDataStore {

    private final RedditClient redditClient;

    /**
     * Construct a {@link RedditDataStore} based on connections to the api (Cloud).
     */
    CloudRedditDataStore(RedditClient redditClient) {
        this.redditClient = redditClient;
    }


    @Override
    public Observable<RedditObject> getRedditsData(String after, int limit) {
        return redditClient.getRedditsData(after,limit);
    }
}
