package com.dafelo.co.redditreader.subreddits.interfaces;


import com.dafelo.co.redditreader.main.presenter.BasePresenter;
import com.dafelo.co.redditreader.main.presenter.BaseView;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;

import java.util.List;

/**
 * Created by root on 24/11/16.
 */

public interface SubRedditListContract {
    interface View extends BaseView {

        void populateAdapter(List<SubReddit> subReddits);
    }

     interface Presenter extends BasePresenter<View> {
         void getSubReddits();
         List<SubReddit> getSubRedditsList();
         void setSubRedditList(List<SubReddit> subReddits);
         String getAfterToken();
         void setAfterToken(String afterToken);

    }
}
