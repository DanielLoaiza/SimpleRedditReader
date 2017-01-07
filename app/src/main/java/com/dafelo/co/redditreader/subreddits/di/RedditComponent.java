package com.dafelo.co.redditreader.subreddits.di;


import com.dafelo.co.redditreader.di.PerActivity;
import com.dafelo.co.redditreader.di.components.ActivityComponent;
import com.dafelo.co.redditreader.di.components.ApplicationComponent;
import com.dafelo.co.redditreader.di.modules.ActivityModule;
import com.dafelo.co.redditreader.subreddits.SubRedditListFragment;

import dagger.Component;

/**
 * Created by root on 24/11/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, RedditModule.class})
public interface RedditComponent extends ActivityComponent {
    void inject(SubRedditListFragment subRedditListFragment);
}
