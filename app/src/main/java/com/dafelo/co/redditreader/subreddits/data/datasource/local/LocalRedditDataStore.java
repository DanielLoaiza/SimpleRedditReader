package com.dafelo.co.redditreader.subreddits.data.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dafelo.co.redditreader.main.domain.RedditListing;
import com.dafelo.co.redditreader.main.domain.RedditObject;
import com.dafelo.co.redditreader.main.domain.schedulers.ObserveOn;
import com.dafelo.co.redditreader.main.domain.schedulers.SubscribeOn;
import com.dafelo.co.redditreader.subreddits.data.datasource.RedditDataStore;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.dafelo.co.redditreader.subreddits.data.datasource.local.RedditDbContract.SubRedditEntry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dafelo on 9/01/17.
 * local data store that stores the data on a sql lite databae
 */
@Singleton
public class LocalRedditDataStore implements RedditDataStore {


    @NonNull
    // database helper that turn cursors into observables
    private final BriteDatabase mDatabaseHelper;

    // mapper function with the information about how to turn a cursor into a redditObject
    @NonNull
    private Func1<Cursor, RedditObject> mRedditMapperFunction;

    @Inject
    public LocalRedditDataStore(@NonNull SubscribeOn schedulerProvider, RedditDbHelper dbHelper) {
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, schedulerProvider.getScheduler());
        mRedditMapperFunction = this::getRedditList;
    }

    /**
     *
     * @param c the cursor returned by the database
     * @return the reddit Object mapped with the cursor info
     */
    private RedditObject getRedditList(@NonNull Cursor c) {

        // asociate the cursor information with the correspondint entity attributes
        String bannerImg  = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_BANNER_IMG));
        String submitTextHtml = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_SUBMIT_TEXT_HTML));
        String submitText = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_SUBMIT_TEXT));
        String displayName = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_DISPLAY_NAME));
        String headerImg = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_HEADER_IMG));
        String descriptionHtml = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_DESCRIPTION_HTML));
        String title = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_TITLE));
        String publicDescriptionHtml = c.getString
                (c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_PUBLIC_DESCRIPTION_HTML));
        String iconImg = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_ICON_IMG));
        String headerTitle = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_HEADER_TITLE));
        String description = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_DESCRIPTION));
        Integer subscribers = c.getInt(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_SUBSCRIBERS));
        String submitTextLabel = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_SUBMIT_TEXT_LABEL));
        String lang = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_LANG));
        String name = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_NAME));
        Integer created = c.getInt(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_CREATED));
        String url = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_URL));
        Integer createdUtc = c.getInt(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_CREATED_UTC));
        String publicDescription = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_PUBLIC_DESCRIPTION));
        String subredditType = c.getString(c.getColumnIndexOrThrow(SubRedditEntry.COLUMN_SUBREDDIT_TYPE));

        return new SubReddit(bannerImg,submitTextHtml,submitText, displayName,
                headerImg, descriptionHtml, title, publicDescriptionHtml,
                iconImg,headerTitle, description, subscribers,
                submitTextLabel, lang, name, created, url, createdUtc,
                publicDescription, subredditType);
    }

    /**
     *
     * @param after
     * @param limit
     * @return an observable reddit object with all the subreddits
     */
    @Override
    public Observable<RedditObject> getRedditsData(String after, int limit) {

        return Observable.create(emmiter -> {
            RedditListing redditObj = new RedditListing();
             mDatabaseHelper.createQuery(SubRedditEntry.TABLE_NAME, "SELECT * FROM " + SubRedditEntry.TABLE_NAME)
                    .mapToList(mRedditMapperFunction).doOnNext(redditObjects -> {
                    redditObj.setChildren(redditObjects);
                    emmiter.onNext(redditObj);
                    emmiter.onCompleted();
            }).doOnError(emmiter::onError).subscribe();
        });

    }

    /**
     *
     * @param subReddits the list of subreddits
     * @return the quantity of rows inserted
     * method that inserts many rows depending on a list
     */
    public int bulkInsert(List<SubReddit> subReddits) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
                db.beginTransaction();
                int returnCount = 0;
                try {
                    ContentValues values = new ContentValues();
                    for (SubReddit subReddit : subReddits) {
                        values.put(SubRedditEntry.COLUMN_BANNER_IMG, subReddit.getBannerImg());
                        values.put(SubRedditEntry.COLUMN_SUBMIT_TEXT_HTML, subReddit.getSubmitTextHtml());
                        values.put(SubRedditEntry.COLUMN_SUBMIT_TEXT, subReddit.getSubmitText());
                        values.put(SubRedditEntry.COLUMN_DISPLAY_NAME, subReddit.getDisplayName());
                        values.put(SubRedditEntry.COLUMN_HEADER_IMG, subReddit.getHeaderImg());
                        values.put(SubRedditEntry.COLUMN_DESCRIPTION_HTML, subReddit.getDescriptionHtml());
                        values.put(SubRedditEntry.COLUMN_TITLE, subReddit.getTitle());
                        values.put(SubRedditEntry.COLUMN_PUBLIC_DESCRIPTION_HTML,
                                subReddit.getPublicDescriptionHtml());
                        values.put(SubRedditEntry.COLUMN_ICON_IMG, subReddit.getIconImg());
                        values.put(SubRedditEntry.COLUMN_HEADER_TITLE, subReddit.getHeaderTitle());
                        values.put(SubRedditEntry.COLUMN_DESCRIPTION, subReddit.getDescription());
                        values.put(SubRedditEntry.COLUMN_SUBSCRIBERS, subReddit.getSubscribers());
                        values.put(SubRedditEntry.COLUMN_SUBMIT_TEXT_LABEL, subReddit.getSubmitTextLabel());
                        values.put(SubRedditEntry.COLUMN_LANG, subReddit.getLang());
                        values.put(SubRedditEntry.COLUMN_NAME, subReddit.getName());
                        values.put(SubRedditEntry.COLUMN_CREATED, subReddit.getCreated());
                        values.put(SubRedditEntry.COLUMN_URL, subReddit.getUrl());
                        values.put(SubRedditEntry.COLUMN_CREATED_UTC, subReddit.getCreatedUtc());
                        values.put(SubRedditEntry.COLUMN_PUBLIC_DESCRIPTION, subReddit.getPublicDescription());
                        values.put(SubRedditEntry.COLUMN_SUBREDDIT_TYPE, subReddit.getSubredditType());
                        long _id = db.insert(SubRedditEntry.TABLE_NAME, null, values);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
        return returnCount;
        }
}

