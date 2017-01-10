package com.dafelo.co.redditreader.subreddits.data.datasource.local;

import android.provider.BaseColumns;

/**
 * Created by root on 9/01/17.
 */

public final class RedditDbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private RedditDbContract() {}


    /* Inner class that defines the table contents */
    public static final class SubRedditEntry implements BaseColumns {
        public static final String TABLE_NAME = "subreddit";
        public static final String COLUMN_BANNER_IMG = "banner_img";
        public static final String COLUMN_SUBMIT_TEXT_HTML = "submit_text_html";
        public static final String COLUMN_SUBMIT_TEXT = "submit_text";
        public static final String COLUMN_DISPLAY_NAME = "display_name";
        public static final String COLUMN_HEADER_IMG = "header_img";
        public static final String COLUMN_DESCRIPTION_HTML = "description_html";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PUBLIC_DESCRIPTION_HTML = "public_description_html";
        public static final String COLUMN_ICON_IMG = "icon_img";
        public static final String COLUMN_HEADER_TITLE = "header_title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_SUBSCRIBERS = "subscribers";
        public static final String COLUMN_SUBMIT_TEXT_LABEL = "submit_text_label";
        public static final String COLUMN_LANG = "lang";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_CREATED_UTC = "created_utc";
        public static final String COLUMN_PUBLIC_DESCRIPTION = "public_description";
        public static final String COLUMN_SUBREDDIT_TYPE = "subreddit_type";
    }
}
