package com.dafelo.co.redditreader.subreddits;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.main.BaseFragment;
import com.dafelo.co.redditreader.subreddits.adapters.SubRedditListAdapter;
import com.dafelo.co.redditreader.subreddits.di.RedditComponent;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.dafelo.co.redditreader.subreddits.presenter.SubRedditListPresenter;

import org.parceler.Parcels;

import java.util.List;


/**
 * Created by root on 7/01/17.
 */

public class SubRedditDetailFragment extends BaseFragment {

    CollapsingToolbarLayout collapsingToolbar;
    ImageView image;
    TextView submitText;
    TextView descriptionText;
    public static final String SUB_REDDIT_ITEM = "com.co.dafelo.item";
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SubRedditDetailFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the component from the activity with the injected dependencies
        this.getComponent(RedditComponent.class).inject(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subreddit_detail, container, false);
        // Set Collapsing Toolbar layout to the screen
        collapsingToolbar =
                (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        image = (ImageView) rootView.findViewById(R.id.image);
        submitText = (TextView)rootView.findViewById(R.id.submit_text);
        descriptionText = (TextView)rootView.findViewById(R.id.description_detail);
        return rootView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            SubReddit subReddit = Parcels.unwrap(getArguments()
                    .getParcelable(SUB_REDDIT_ITEM));
        showData(subReddit);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mPresenter = null;
    }
    public void showData(SubReddit subReddit) {
        collapsingToolbar.setTitle(subReddit.getTitle());
        String url = subReddit.getBannerImg() != null ? subReddit.getBannerImg(): subReddit.getHeaderImg();
        Glide
                .with(getActivity())
                .load(url)
                .centerCrop()
                .crossFade()
                .into(image);
        submitText.setText(subReddit.getSubmitText());
        descriptionText.setText(subReddit.getDescription());
    }
}
