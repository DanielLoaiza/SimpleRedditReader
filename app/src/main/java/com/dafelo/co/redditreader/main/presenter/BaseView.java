package com.dafelo.co.redditreader.main.presenter;

/**
 * Created by root on 25/08/16.
 * generic interface that all views must implement
 */
public interface BaseView {

    void showSpinner();
    void hideSpinner();
    void showError(Throwable e);

}
