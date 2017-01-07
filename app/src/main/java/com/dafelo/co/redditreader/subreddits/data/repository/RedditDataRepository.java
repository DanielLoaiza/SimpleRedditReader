package com.dafelo.co.redditreader.subreddits.data.repository;


import com.dafelo.co.redditreader.main.domain.RedditObject;
import com.dafelo.co.redditreader.subreddits.data.datasource.RedditDataStore;
import com.dafelo.co.redditreader.subreddits.data.datasource.RedditDataStoreFactory;
import com.dafelo.co.redditreader.subreddits.domain.usecase.RedditRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by root on 24/11/16.
 */

public class RedditDataRepository implements RedditRepository {

    private final RedditDataStoreFactory redditDataStoreFactory;

    /**
     * Constructs a {@link RedditRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     */
    @Inject
    public RedditDataRepository(RedditDataStoreFactory dataStoreFactory) {
        this.redditDataStoreFactory = dataStoreFactory;
    }
    @Override
    public Observable<RedditObject> getRedditsData(String after, int limit) {
        final RedditDataStore redditDataStore = this.redditDataStoreFactory.createCloudDataStore();
        return redditDataStore.getRedditsData(after, limit);
    }
}
