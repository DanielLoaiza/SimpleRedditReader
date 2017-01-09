package com.dafelo.co.redditreader.subreddits.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dafelo.co.redditreader.main.domain.RedditListing;
import com.dafelo.co.redditreader.main.domain.interactors.DefaultSubscriber;
import com.dafelo.co.redditreader.main.domain.interactors.UseCase;
import com.dafelo.co.redditreader.main.domain.interactors.UseCaseData;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.dafelo.co.redditreader.subreddits.domain.usecase.DTO.RedditsQueryData;
import com.dafelo.co.redditreader.subreddits.interfaces.SubRedditListContract;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

/**
 * Created by root on 24/11/16.
 */

public class SubRedditListPresenter implements SubRedditListContract.Presenter {

    @NonNull
    private SubRedditListContract.View mSubRedditView;
    private int pageLimit;
    private String afterToken;
    private List<SubReddit> subRedditList;
    private final UseCase getSubRedditsListUseCase;
    public final static String REDDIT_ITEMS = "com.dafelo.co.redditreader.subreddit_list";
    public final static String AFTER_TOKEN = "com.dafelo.co.redditreader.subreddit_after_token";

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
    public void setSubRedditList(List<SubReddit> subReddits) {
        subRedditList = subReddits;
        mSubRedditView.populateAdapter(subReddits);
    }

    @Override
    public List<SubReddit> getSubRedditsList() {
        return subRedditList;
    }

    @Override
    public String getAfterToken() {
        return afterToken;
    }

    @Override
    public void setAfterToken(String afterToken) {
        this.afterToken = afterToken;
    }

    @Override
    public void setView(SubRedditListContract.View view) {
        mSubRedditView = view;
    }

    @Override
    public void unsubscribe() {
        mSubRedditView = null;
        getSubRedditsListUseCase.unsubscribe();
    }

    private final class SubRedditListSubscriber extends DefaultSubscriber<RedditListing> {

        @Override public void onCompleted() {
            // do nothing
        }

        @Override public void onError(Throwable e) {
            Log.e("error", e.getMessage());
        }

        @Override public void onNext(RedditListing redditList) {
            afterToken = redditList.getAfter();
            // turn the list of children into an observable
            Observable.from(redditList.getChildren())
                    // iterate over every item and cast to subReddit Obj
                    .map(redditObject -> (SubReddit) redditObject)
                    // turns the stream into a list
                    .toList()
                    // sends the list to the view
                    .doOnNext(SubRedditListPresenter.this::setSubRedditList)
                    .doOnError(throwable -> mSubRedditView.showError(throwable))
                    .subscribe();
        }
    }
}
