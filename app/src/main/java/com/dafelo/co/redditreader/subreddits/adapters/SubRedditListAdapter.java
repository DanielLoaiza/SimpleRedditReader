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

public class SubRedditListAdapter extends RecyclerView.Adapter<SubRedditListAdapter.ViewHolder> {

    private List<SubReddit> mSubReddits;
    private Context mContext;

    public SubRedditListAdapter(Context context) {
        mContext = context;
    }

    public void setSubRedditsList(List<SubReddit> subReddits) {
        mSubReddits =  subReddits;
        this.notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubReddit subReddit = mSubReddits.get(position);
        holder.title.setText(subReddit.getTitle());
        holder.text.setText(subReddit.getPublicDescription());
        holder.subscribers.setText(String.valueOf(subReddit.getSubscribers()));
        String url = subReddit.getBannerImg().isEmpty() ? subReddit.getHeaderImg() : subReddit.getBannerImg();
        Glide
                .with(mContext)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return (mSubReddits != null) ? mSubReddits.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        TextView text;
        TextView subscribers;

        ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tile_title);
            image = (ImageView) view.findViewById(R.id.tile_picture);
            text = (TextView) view.findViewById(R.id.card_text);
            subscribers = (TextView) view.findViewById(R.id.subscribers);

        }
    }
}
