package com.dafelo.co.redditreader.main;

import android.os.Bundle;
import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.di.HasComponent;
import com.dafelo.co.redditreader.subreddits.SubRedditListFragment;
import com.dafelo.co.redditreader.subreddits.di.DaggerRedditComponent;
import com.dafelo.co.redditreader.subreddits.di.RedditComponent;
import com.dafelo.co.redditreader.subreddits.di.RedditModule;


public class MainActivity extends BaseActivity implements HasComponent<RedditComponent> {

    private RedditComponent redditComponent;
    private SubRedditListFragment subRedditListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();
        if(savedInstanceState == null) {
            subRedditListFragment = new SubRedditListFragment();
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
