package com.dafelo.co.redditreader.di.modules;

import android.content.Context;

import com.dafelo.co.redditreader.RedditReader;
import com.dafelo.co.redditreader.main.domain.schedulers.ObserveOn;
import com.dafelo.co.redditreader.main.domain.schedulers.SubscribeOn;
import com.dafelo.co.redditreader.main.domain.threads.NewThread;
import com.dafelo.co.redditreader.main.domain.threads.UIThread;
import com.dafelo.co.redditreader.subreddits.data.repository.RedditDataRepository;
import com.dafelo.co.redditreader.subreddits.domain.usecase.RedditRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * A module to wrap the application state and expose it to the graph.
 */
@Module
public class ApplicationModule {
    private final RedditReader application;

    public ApplicationModule(RedditReader application) {
        this.application = application;
    }

    @Provides
    @Singleton
    // provides the application context
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    /**
     * provides the observeOn
     * @param UIThread, the concrete implementation of the interface ObserveOn
     *                  that corresponds to the UIThread
     */
    ObserveOn provideObserveOn(UIThread observeOn) {
        return observeOn;
    }

    @Provides
    @Singleton
    /**
     * provides the SubscribeOn
     * @param NewThread, the concrete implementation of the interface SubscribeOn
     *                  that corresponds to a NewThread
     */
    SubscribeOn provideSubscribeOn(NewThread subscribeOn) {
        return subscribeOn;
    }

    @Provides @Singleton
    /**
     * provides the RedditRepository
     * @param RedditDataRepository, the concrete implementation of the interface
     *                              RedditRepositor that corresponds to a RedditDataRepository
     */
    RedditRepository provideRedditRepository(RedditDataRepository redditDataRepository) {
        return redditDataRepository;
    }



}
