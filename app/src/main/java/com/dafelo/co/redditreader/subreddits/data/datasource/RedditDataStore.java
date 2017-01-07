package com.dafelo.co.redditreader.subreddits.data.datasource;

import com.dafelo.co.redditreader.main.domain.RedditObject;

import rx.Observable;

/**
 * Created by root on 25/11/16.
 */

public interface RedditDataStore {
    Observable<RedditObject> getRedditsData(String after, int limit);
}
