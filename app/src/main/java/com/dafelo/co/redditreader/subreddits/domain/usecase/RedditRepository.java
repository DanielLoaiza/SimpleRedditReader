package com.dafelo.co.redditreader.subreddits.domain.usecase;

import com.dafelo.co.redditreader.main.domain.RedditObject;

import rx.Observable;

/**
 * Created by root on 24/11/16.
 */

public interface RedditRepository {
    Observable<RedditObject> getRedditsData(String after, int limit);
}
