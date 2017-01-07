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


@Module
public class ApplicationModule {
    private final RedditReader application;

    public ApplicationModule(RedditReader application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ObserveOn provideObserveOn(UIThread observeOn) {
        return observeOn;
    }

    @Provides
    @Singleton
    SubscribeOn provideSubscribeOn(NewThread subscribeOn) {
        return subscribeOn;
    }

    @Provides @Singleton
    RedditRepository provideRedditRepository(RedditDataRepository redditDataRepository) {
        return redditDataRepository;
    }


}
