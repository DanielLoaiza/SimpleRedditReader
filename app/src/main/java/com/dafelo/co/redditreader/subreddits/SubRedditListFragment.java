package com.dafelo.co.redditreader.subreddits;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.main.BaseFragment;
import com.dafelo.co.redditreader.subreddits.adapters.SubRedditListAdapter;
import com.dafelo.co.redditreader.subreddits.di.RedditComponent;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.dafelo.co.redditreader.subreddits.interfaces.SubRedditListContract;
import com.dafelo.co.redditreader.subreddits.presenter.SubRedditListPresenter;


import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by root on 7/01/17.
 */

public class SubRedditListFragment extends BaseFragment implements SubRedditListContract.View {

    RecyclerView mRecyclerView;
    SubRedditListAdapter mAdapter;
    @Inject SubRedditListContract.Presenter mPresenter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SubRedditListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RedditComponent.class).inject(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_container, container, false);
        mAdapter = new SubRedditListAdapter(getActivity());
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        setupRecyclerView();
        return rootView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        if(savedInstanceState != null) {
            List<SubReddit> subReddits = Parcels.unwrap(savedInstanceState
                    .getParcelable(SubRedditListPresenter.REDDIT_ITEMS));
            String afterToken = savedInstanceState.getString(SubRedditListPresenter.AFTER_TOKEN);
            mPresenter.setAfterToken(afterToken);
            mPresenter.setSubRedditList(subReddits);

        }
        if (savedInstanceState == null) {
            mPresenter.getSubReddits();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
        mRecyclerView.setAdapter(null);
    }

    public void setupRecyclerView() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            mAdapter.setOrientation(true);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT){
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setOrientation(false);
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void populateAdapter(List<SubReddit> subReddits) {
        mAdapter.setSubRedditsList(subReddits);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mPresenter = null;
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Parcelable wrapped = Parcels.wrap(mPresenter.getSubRedditsList());
        outState.putString(SubRedditListPresenter.AFTER_TOKEN, mPresenter.getAfterToken());
        outState.putParcelable(SubRedditListPresenter.REDDIT_ITEMS, wrapped);
        super.onSaveInstanceState(outState);
    }
}
