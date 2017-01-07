package com.dafelo.co.redditreader.main.presenter;

/**
 * Created by root on 25/08/16.
 */
public interface BaseView {

    void showSpinner();
    void hideSpinner();
    void showError(Throwable e);

}
