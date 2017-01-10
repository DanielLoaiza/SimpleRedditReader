package com.dafelo.co.redditreader.subreddits.data.datasource.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dafelo.co.redditreader.subreddits.data.datasource.local.RedditDbContract.SubRedditEntry;

import javax.inject.Inject;

/**
 * Created by root on 9/01/17.
 * helper class that creates the reddit database
 */

public class RedditDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Subreddits.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String NOT_NULL = " NOT NULL";

    private static final String COMMA_SEP = ",";

    @Inject
    public RedditDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_SUBRREDITS_TABLE = "CREATE TABLE " + SubRedditEntry.TABLE_NAME + " (" +
                SubRedditEntry._ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
                SubRedditEntry.COLUMN_TITLE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                SubRedditEntry.COLUMN_BANNER_IMG + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_CREATED + INTEGER_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_CREATED_UTC + INTEGER_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_DESCRIPTION_HTML + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_DISPLAY_NAME + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_LANG + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_ICON_IMG + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_HEADER_IMG + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_HEADER_TITLE + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_PUBLIC_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_URL + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_SUBSCRIBERS + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_SUBREDDIT_TYPE + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_SUBMIT_TEXT_LABEL + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_SUBMIT_TEXT + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_PUBLIC_DESCRIPTION_HTML + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_SUBMIT_TEXT_HTML + TEXT_TYPE + COMMA_SEP +
                SubRedditEntry.COLUMN_NAME + TEXT_TYPE +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_SUBRREDITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SubRedditEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
