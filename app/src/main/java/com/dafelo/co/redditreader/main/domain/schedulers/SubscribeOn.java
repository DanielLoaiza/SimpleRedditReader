package com.dafelo.co.redditreader.main.domain.schedulers;

/**
 * Created by root on 23/10/16.
 */

import rx.Scheduler;
/**
 * interface that provides a scheduler to subscribeOn
 */
public interface SubscribeOn {
    Scheduler getScheduler();
}
