package com.dafelo.co.redditreader.subreddits.domain.usecase.DTO;


import com.dafelo.co.redditreader.main.domain.interactors.UseCaseData;

/**
 * Created by root on 24/10/16.
 */

public class RedditsQueryData implements UseCaseData {
    private final String after;
    private final int limit;

    public RedditsQueryData(String after, int limit) {
        this.after = after;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public String getAfter() {
        return after;
    }
}
