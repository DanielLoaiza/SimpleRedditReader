package com.dafelo.co.redditreader.subreddits.di;

/**
 * Created by root on 24/11/16.
 */


import com.dafelo.co.redditreader.di.PerActivity;
import com.dafelo.co.redditreader.main.domain.interactors.UseCase;
import com.dafelo.co.redditreader.main.domain.schedulers.ObserveOn;
import com.dafelo.co.redditreader.main.domain.schedulers.SubscribeOn;
import com.dafelo.co.redditreader.subreddits.domain.usecase.GetSubRedditsListUseCase;
import com.dafelo.co.redditreader.subreddits.domain.usecase.RedditRepository;
import com.dafelo.co.redditreader.subreddits.interfaces.SubRedditListContract;
import com.dafelo.co.redditreader.subreddits.presenter.SubRedditListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class RedditModule {

    @Provides
    @PerActivity
    SubRedditListContract.Presenter providePresenter(SubRedditListPresenter subRedditListPresenter){
        return subRedditListPresenter;
    }

    @Provides
    @PerActivity
    @Named("subRedditsList")
    UseCase provideSubRedditsListUseCase(
            RedditRepository redditRepository,
            SubscribeOn subscribeOn, ObserveOn observeOn) {
        return new GetSubRedditsListUseCase(redditRepository, subscribeOn, observeOn);
    }
}
