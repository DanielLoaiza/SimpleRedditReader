package com.dafelo.co.redditreader;

import android.app.Application;
import android.content.Context;

import com.dafelo.co.redditreader.di.components.ApplicationComponent;
import com.dafelo.co.redditreader.di.components.DaggerApplicationComponent;
import com.dafelo.co.redditreader.di.modules.ApplicationModule;


public class RedditReader extends Application {
    private ApplicationComponent applicationComponent;



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }


}
