package com.dafelo.co.redditreader.subreddits.interfaces;


import com.dafelo.co.redditreader.main.presenter.BasePresenter;
import com.dafelo.co.redditreader.main.presenter.BaseView;

import java.util.List;

/**
 * Created by root on 24/11/16.
 */

public interface SubRedditListContract {
    interface View extends BaseView {

        void populateAdapter();
    }

     interface Presenter extends BasePresenter<View> {
        void getSubReddits();
    }
}
