package com.dafelo.co.redditreader.main.domain;

import java.util.List;

/**
 * Created by root on 7/01/17.
 */

public class RedditListing implements RedditObject {

    private List<RedditObject> children;
    private String before;
    private String after;

    public List<RedditObject> getChildren() {
        return children;
    }

    public void setChildren(List<RedditObject> children) {
        this.children = children;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
