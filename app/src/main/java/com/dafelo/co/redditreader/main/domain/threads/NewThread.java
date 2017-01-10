package com.dafelo.co.redditreader.main.domain.threads;



import com.dafelo.co.redditreader.main.domain.schedulers.SubscribeOn;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by root on 24/10/16.
 */

/**
 * NewThread implementation based on a {@link Scheduler}
 * which will execute actions on a new thread
 */
@Singleton
public class NewThread implements SubscribeOn {
    @Inject
    public NewThread() {}

    @Override
    public Scheduler getScheduler() {
        return Schedulers.newThread();
    }
}
