package com.dafelo.co.redditreader.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;

import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.di.HasComponent;
import com.dafelo.co.redditreader.subreddits.SubRedditDetailFragment;
import com.dafelo.co.redditreader.subreddits.SubRedditListFragment;
import com.dafelo.co.redditreader.subreddits.di.DaggerRedditComponent;
import com.dafelo.co.redditreader.subreddits.di.RedditComponent;
import com.dafelo.co.redditreader.subreddits.di.RedditModule;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.dafelo.co.redditreader.subreddits.listeners.OnSubRedditSelectedListener;
import com.dafelo.co.redditreader.subreddits.presenter.SubRedditListPresenter;

import org.parceler.Parcels;

/**
 * Application main activity
 */
public class MainActivity extends BaseActivity implements HasComponent<RedditComponent>, OnSubRedditSelectedListener {

    private RedditComponent redditComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializes the dagger component for DI
        this.initializeInjector();
        // validates if there is no savedInstance state
        // this way avoids to recreate the fragment on rotation changes
        if(savedInstanceState == null) {
            SubRedditListFragment subRedditListFragment = new SubRedditListFragment();
            addFragment(R.id.reddit_list_container, subRedditListFragment);
        }
    }

    private void initializeInjector() {
        this.redditComponent = DaggerRedditComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .redditModule(new RedditModule())
                .build();
    }

    @Override
    public RedditComponent getComponent() {
        return redditComponent;
    }

    @Override
    public void onSubredditItemClick(SubReddit subReddit) {
        SubRedditDetailFragment newFragment = new SubRedditDetailFragment();
        Bundle args = new Bundle();
        Parcelable wrapped = Parcels.wrap(subReddit);
        args.putParcelable(SubRedditDetailFragment.SUB_REDDIT_ITEM, wrapped);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);

        transaction.replace(R.id.reddit_list_container, newFragment);

        // Commit the transaction
        transaction.commit();
    }
}
