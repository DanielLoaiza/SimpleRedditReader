package com.dafelo.co.redditreader.main.domain.interactors;


import com.dafelo.co.redditreader.main.domain.schedulers.ObserveOn;
import com.dafelo.co.redditreader.main.domain.schedulers.SubscribeOn;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by dafelo on 23/10/16.
 */

/**
 * Generic use Case class with all the methods required to execute the use Case
 * all use cases must extend this class
 */
public abstract class UseCase {

    // the thread where the use case is subscribed on
    private final SubscribeOn subscribeOn;
    // the thread where the use case is observing on
    private final ObserveOn observeOn;
    // generic interface used to pass dynamic data to use cases
    protected   UseCaseData useCaseData;

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(SubscribeOn subscribeOn, ObserveOn observeOn) {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The listener to the observable build
     *                          with {@link #buildUseCaseObservable()}.
     */
    public void execute(Subscriber useCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(subscribeOn.getScheduler())
                .observeOn(observeOn.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void setData(UseCaseData data){
        this.useCaseData = data;
    }
}
