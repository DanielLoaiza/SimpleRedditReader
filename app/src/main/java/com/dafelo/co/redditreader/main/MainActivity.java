package com.dafelo.co.redditreader.main;

import android.os.Bundle;
import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.di.HasComponent;
import com.dafelo.co.redditreader.subreddits.SubRedditListFragment;
import com.dafelo.co.redditreader.subreddits.di.DaggerRedditComponent;
import com.dafelo.co.redditreader.subreddits.di.RedditComponent;
import com.dafelo.co.redditreader.subreddits.di.RedditModule;

/**
 * Application main activity
 */
public class MainActivity extends BaseActivity implements HasComponent<RedditComponent> {

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
}
