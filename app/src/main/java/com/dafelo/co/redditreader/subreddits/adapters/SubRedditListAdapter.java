package com.dafelo.co.redditreader.subreddits.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dafelo.co.redditreader.R;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.dafelo.co.redditreader.subreddits.listeners.OnSubRedditSelectedListener;

import java.util.List;

/**
 * Created by root on 8/01/17.
 * Adapter that shows the corresponding layout depending on device orientation
 */

public class SubRedditListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // the list of subreddits
    private List<SubReddit> mSubReddits;
    // the context
    private Context mContext;
    // the device orientation (true for landscape, false for portrait)
    private boolean orientation;
    // constants used to identify the device orientation and tell the adapter
    // what layout to show
    private final static int LANDSCAPE = 1;
    private final static int PORTRAIT = 0;
    private OnSubRedditSelectedListener onItemSelectedListener;

    public SubRedditListAdapter(Context context) {
        mContext = context;
    }

    public void setSubRedditsList(List<SubReddit> subReddits) {
        mSubReddits =  subReddits;
        this.notifyDataSetChanged();
    }

    public void setOnItemSelectedListener(OnSubRedditSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    // every time the device rotation changes calls this method
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    @Override
    public int getItemViewType(int position) {
        return orientation ? LANDSCAPE : PORTRAIT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_list_item, parent, false);
        RecyclerView.ViewHolder vh;
        // creates different viewholders depending on the device orientation
        if(viewType == LANDSCAPE) {
            vh =  new LandscapeViewHolder(view);
        } else {
            vh =  new PortraitViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SubReddit subReddit = mSubReddits.get(position);
        // validates what kind of viewholders comes to set the data properly
        try {
            String url = subReddit.getBannerImg().isEmpty() ? subReddit.getHeaderImg() : subReddit.getBannerImg();
            if(holder instanceof LandscapeViewHolder) {
                ((LandscapeViewHolder)holder).title.setText(subReddit.getTitle());
                Glide
                        .with(mContext)
                        .load(url)
                        .centerCrop()
                        .crossFade()
                        .into(((LandscapeViewHolder)holder).image);
                ((LandscapeViewHolder) holder).image.setOnClickListener(view ->
                        onItemSelectedListener.onSubredditItemClick(mSubReddits.get(position)));
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
                ((PortraitViewHolder) holder).more.setOnClickListener(view ->
                        onItemSelectedListener.onSubredditItemClick(mSubReddits.get(position)));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        Button more;

        PortraitViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tile_title);
            image = (ImageView) view.findViewById(R.id.tile_picture);
            text = (TextView) view.findViewById(R.id.card_text);
            subscribers = (TextView) view.findViewById(R.id.subscribers);
            more = (Button) view.findViewById(R.id.action_button);

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
