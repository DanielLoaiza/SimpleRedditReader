package com.dafelo.co.redditreader.main.domain.schedulers;

/**
 * Created by root on 23/10/16.
 */

import rx.Scheduler;

public interface ObserveOn {
    Scheduler getScheduler();
}