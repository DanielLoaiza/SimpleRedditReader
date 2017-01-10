package com.dafelo.co.redditreader.di.components;

import android.content.Context;


import com.dafelo.co.redditreader.di.modules.ApplicationModule;
import com.dafelo.co.redditreader.main.BaseActivity;
import com.dafelo.co.redditreader.main.domain.schedulers.ObserveOn;
import com.dafelo.co.redditreader.main.domain.schedulers.SubscribeOn;
import com.dafelo.co.redditreader.subreddits.domain.usecase.RedditRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 * root component that exposes main classes required by the subgraphs
 */
    @Singleton // Constraints this component to one-per-application or unscoped bindings.
    @Component(modules = ApplicationModule.class)
    public interface ApplicationComponent {
        void inject(BaseActivity baseActivity);

        //Exposed to sub-graphs.
    // the context of the application
        Context context();
    // the thread where the request observe
        ObserveOn observeOn();
    // the thread where the request are subscribed
        SubscribeOn subscribeOn();
    // the repository through all request are made
        RedditRepository redditRepository();
}
