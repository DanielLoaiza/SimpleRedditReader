package com.dafelo.co.redditreader.subreddits;

import android.content.Context;
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
import com.dafelo.co.redditreader.subreddits.listeners.OnSubRedditSelectedListener;
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
    private OnSubRedditSelectedListener mCallback;


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
        // get the component from the activity with the injected dependencies
        this.getComponent(RedditComponent.class).inject(this);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnSubRedditSelectedListener) {
            try {
                mCallback = (OnSubRedditSelectedListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString()
                        + " must implement OnSubredditSelectedListener");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_container, container, false);
        // init data
        mAdapter = new SubRedditListAdapter(getActivity());
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        setupRecyclerView();
        return rootView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        // if there is any savedState is restored
        if(savedInstanceState != null) {
            List<SubReddit> subReddits = Parcels.unwrap(savedInstanceState
                    .getParcelable(SubRedditListPresenter.REDDIT_ITEMS));
            String afterToken = savedInstanceState.getString(SubRedditListPresenter.AFTER_TOKEN);
            mPresenter.setAfterToken(afterToken);
            mPresenter.setSubRedditList(subReddits);

        }
        // if no savedState get the subreddits from presenter
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

    /**
     * method that set the layout manager for the recycler view depending on his orientation
     */
    public void setupRecyclerView() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            mAdapter.setOrientation(true);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setOrientation(false);
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void populateAdapter(List<SubReddit> subReddits) {
        mAdapter.setSubRedditsList(subReddits);
        mAdapter.setOnItemSelectedListener(mCallback);
    }

    @Override
    public void onDetach() {
        mCallback = null;
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
        // wraps the data on the bundle to restore the state later
        Parcelable wrapped = Parcels.wrap(mPresenter.getSubRedditsList());
        outState.putString(SubRedditListPresenter.AFTER_TOKEN, mPresenter.getAfterToken());
        outState.putParcelable(SubRedditListPresenter.REDDIT_ITEMS, wrapped);
        super.onSaveInstanceState(outState);
    }
}
