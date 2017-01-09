package com.dafelo.co.redditreader.subreddits.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;

import java.util.List;

/**
 * Created by root on 8/01/17.
 */

public class SubRedditListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubReddit> mSubReddits;
    private Context mContext;
    private boolean orientation;
    public final int LANDSCAPE = 1;
    private final int PORTRAIT = 0;

    public SubRedditListAdapter(Context context) {
        mContext = context;
    }

    public void setSubRedditsList(List<SubReddit> subReddits) {
        mSubReddits =  subReddits;
        this.notifyDataSetChanged();
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    @Override
    public int getItemViewType(int position) {
        return orientation ? LANDSCAPE : PORTRAIT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder vh;
        if(viewType == LANDSCAPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_list_item_land, parent, false);
            vh =  new LandscapeViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_list_item, parent, false);
            vh =  new PortraitViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SubReddit subReddit = mSubReddits.get(position);
        String url = subReddit.getBannerImg().isEmpty() ? subReddit.getHeaderImg() : subReddit.getBannerImg();
        if(holder instanceof LandscapeViewHolder) {
            ((LandscapeViewHolder)holder).title.setText(subReddit.getTitle());
            Glide
                    .with(mContext)
                    .load(url)
                    .centerCrop()
                    .crossFade()
                    .into(((LandscapeViewHolder)holder).image);
        } else {
            ((PortraitViewHolder)holder).title.setText(subReddit.getTitle());
            ((PortraitViewHolder)holder).text.setText(subReddit.getPublicDescription());
            ((PortraitViewHolder)holder).subscribers.setText(String.valueOf(subReddit.getSubscribers()));
            Glide
                    .with(mContext)
                    .load(url)
                    .centerCrop()
                    .crossFade()
                    .into(((PortraitViewHolder)holder).image);
        }
    }

    @Override
    public int getItemCount() {
        return (mSubReddits != null) ? mSubReddits.size() : 0;
    }

    private static class PortraitViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        TextView text;
        TextView subscribers;

        PortraitViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tile_title);
            image = (ImageView) view.findViewById(R.id.tile_picture);
            text = (TextView) view.findViewById(R.id.card_text);
            subscribers = (TextView) view.findViewById(R.id.subscribers);

        }
    }

     private static class LandscapeViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        LandscapeViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tile_title);
            image = (ImageView) v.findViewById(R.id.tile_picture);
        }
    }
}
