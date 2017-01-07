package com.dafelo.co.redditreader.subreddits.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dafelo.co.redditreader.main.domain.RedditListing;
import com.dafelo.co.redditreader.main.domain.interactors.DefaultSubscriber;
import com.dafelo.co.redditreader.main.domain.interactors.UseCase;
import com.dafelo.co.redditreader.main.domain.interactors.UseCaseData;
import com.dafelo.co.redditreader.subreddits.domain.usecase.DTO.RedditsQueryData;
import com.dafelo.co.redditreader.subreddits.interfaces.SubRedditListContract;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by root on 24/11/16.
 */

public class SubRedditListPresenter implements SubRedditListContract.Presenter {

    @NonNull
    private SubRedditListContract.View mSubRedditView;
    private int pageLimit;
    private String afterToken;
    private final UseCase getSubRedditsListUseCase;

    @Inject
    public SubRedditListPresenter(@Named("subRedditsList") UseCase getSubRedditsListUseCase) {
        this.getSubRedditsListUseCase = getSubRedditsListUseCase;
        pageLimit = 20;
        afterToken = null;
    }

    @Override
    public void getSubReddits() {
        UseCaseData queryData = new RedditsQueryData(afterToken, pageLimit);
        this.getSubRedditsListUseCase.setData(queryData);
        this.getSubRedditsListUseCase.execute(new SubRedditListSubscriber());
    }

    @Override
    public void setView(SubRedditListContract.View view) {
        mSubRedditView = view;
    }

    @Override
    public void unsubscribe() {
        this.getSubRedditsListUseCase.unsubscribe();
    }

    private final class SubRedditListSubscriber extends DefaultSubscriber<RedditListing> {

        @Override public void onCompleted() {
            // do nothing
        }

        @Override public void onError(Throwable e) {
            Log.e("error", e.getMessage());
        }

        @Override public void onNext(RedditListing redditList) {

        }
    }
}
