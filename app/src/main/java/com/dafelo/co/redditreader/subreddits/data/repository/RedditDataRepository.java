package com.dafelo.co.redditreader.subreddits.data.repository;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.dafelo.co.redditreader.main.domain.RedditListing;
import com.dafelo.co.redditreader.main.domain.RedditObject;
import com.dafelo.co.redditreader.subreddits.data.datasource.RedditDataStore;
import com.dafelo.co.redditreader.subreddits.data.datasource.RedditDataStoreFactory;
import com.dafelo.co.redditreader.subreddits.data.datasource.local.LocalRedditDataStore;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.dafelo.co.redditreader.subreddits.domain.usecase.RedditRepository;
import com.google.common.annotations.VisibleForTesting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;

import rx.Observable;

/**
 * Created by root on 24/11/16.
 */

public class RedditDataRepository implements RedditRepository {

    private final RedditDataStoreFactory redditDataStoreFactory;

    @VisibleForTesting
    @Nullable
    Map<String, RedditObject> mCachedObjects;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = true;

    private Context context;

    /**
     * Constructs a {@link RedditRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     */
    @Inject
    public RedditDataRepository(RedditDataStoreFactory dataStoreFactory, Context context) {
        this.redditDataStoreFactory = dataStoreFactory;
        this.context = context;
    }
    @Override
    public Observable<RedditObject> getRedditsData(String after, int limit) {
        if (mCachedObjects != null && !mCacheIsDirty) {
            return Observable.from(mCachedObjects.values());
        } else if (mCachedObjects == null) {
            mCachedObjects = new LinkedHashMap<>();
        }

        Observable<RedditObject> remoteRedditData = getAndSaveRemoteRedditData(after, limit);

        if (mCacheIsDirty && isThereInternetConnection()) {
            return remoteRedditData;
        } else {
            // Query the local storage if available. If not, query the network.
            return getAndCacheLocalRedditData(after, limit);
        }
    }

    private Observable<RedditObject> getAndSaveRemoteRedditData(String after, int limit) {
        LocalRedditDataStore localRedditDataStore = (LocalRedditDataStore)redditDataStoreFactory
                .createLocalDataStore();
        return redditDataStoreFactory
                .createCloudDataStore()
                .getRedditsData(after, limit)
                .doOnNext(redditObject -> {
                    RedditListing redditListing = (RedditListing)redditObject;
                     Observable.from(redditListing.getChildren())
                            .map(redditObject1 -> (SubReddit) redditObject1)
                            .toList()
                             .doOnNext(subReddits -> {
                                 int cont = localRedditDataStore.bulkInsert(subReddits);
                                 mCachedObjects.put(redditListing.getAfter(), redditObject);
                             }).subscribe();
                }).doOnCompleted(() -> mCacheIsDirty = false);
    }

    private Observable<RedditObject> getAndCacheLocalRedditData(String after, int limit) {
        return redditDataStoreFactory.
                createLocalDataStore()
                .getRedditsData(after, limit)
                .doOnNext(redditObject -> {
                    RedditListing redditListing = (RedditListing)redditObject;
                    Observable.from(redditListing.getChildren())
                            .map(redditObject1 -> (SubReddit) redditObject1)
                            .toList()
                            .doOnNext(subReddits ->
                                    mCachedObjects.put(redditListing.getAfter(), redditObject))
                    .subscribe();
                });
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
