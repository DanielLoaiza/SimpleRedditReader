package com.dafelo.co.redditreader.subreddits;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.main.BaseFragment;
import com.dafelo.co.redditreader.subreddits.di.RedditComponent;
import com.dafelo.co.redditreader.subreddits.interfaces.SubRedditListContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by root on 7/01/17.
 */

public class SubRedditListFragment extends BaseFragment implements SubRedditListContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private Unbinder unbinder;
    //private PlateListAdapter mAdapter;
    @Inject
    SubRedditListContract.Presenter mPresenter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SubRedditListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RedditComponent.class).inject(this);
        mPresenter.setView(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter.getSubReddits();

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
        unbinder.unbind();
    }

    @Override
    public void populateAdapter() {

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
}
