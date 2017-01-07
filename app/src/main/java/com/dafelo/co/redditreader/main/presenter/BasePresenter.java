package com.dafelo.co.redditreader.main.presenter;

/**
 * Created by root on 25/08/16.
 */
public interface BasePresenter<T> {

    void setView(T view);
    void unsubscribe();

}
