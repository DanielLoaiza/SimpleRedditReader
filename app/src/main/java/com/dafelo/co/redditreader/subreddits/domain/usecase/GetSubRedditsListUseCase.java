package com.dafelo.co.redditreader.subreddits.domain.usecase;

/**
 * Created by root on 24/11/16.
 */


import com.dafelo.co.redditreader.main.domain.interactors.UseCase;
import com.dafelo.co.redditreader.main.domain.schedulers.ObserveOn;
import com.dafelo.co.redditreader.main.domain.schedulers.SubscribeOn;
import com.dafelo.co.redditreader.subreddits.domain.usecase.DTO.RedditsQueryData;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by root on 23/10/16.
 */

public class GetSubRedditsListUseCase extends UseCase {

    private final RedditRepository subRedditRepository;

    @Inject
    public GetSubRedditsListUseCase(RedditRepository subRedditRepository, SubscribeOn subscribeOn,
                                    ObserveOn observeOn) {
        super(subscribeOn, observeOn);
        this.subRedditRepository = subRedditRepository;

    }
    @Override
    protected Observable buildUseCaseObservable() {

        if(useCaseData == null || !(useCaseData instanceof RedditsQueryData)) {
            throw new IllegalArgumentException("Need to provide data through use case data");
        }
        RedditsQueryData redditsQuery = (RedditsQueryData) useCaseData;
        if(redditsQuery.getLimit() <= 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        return this.subRedditRepository.getRedditsData(redditsQuery.getAfter(),
                redditsQuery.getLimit());
    }
}
