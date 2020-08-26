package com.bicubic.tennis.adapter;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View.OnClickListener;

import com.bicubic.tennis.R;
import com.bicubic.tennis.activity.MainActivity;
import com.bicubic.tennis.fragment.FeedDialogFragment;
import com.bicubic.tennis.model.Feed;

import java.util.List;

/**
 * Created by admin on 8/5/2016.
 */
public class RVFeedAdapter extends RecyclerView.Adapter<RVFeedAdapter.FeedHolder> {

    static Context context;
    List<Feed> feedList;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public RVFeedAdapter(List<Feed> feedList, Context context) {
        this.feedList = feedList;
        this.context = context;
    }

    public static class FeedHolder extends RecyclerView.ViewHolder implements OnClickListener {

        ImageView img_main;
        TextView tv_title;
        Button bt_facebook, bt_twitter, bt_share, bt_comment;

        public FeedHolder(View itemView) {
            super(itemView);

            img_main = (ImageView) itemView.findViewById(R.id.img_main);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            bt_facebook = (Button) itemView.findViewById(R.id.bt_facebook);
            bt_twitter = (Button) itemView.findViewById(R.id.bt_twitter);
            bt_share = (Button) itemView.findViewById(R.id.bt_share);
//            bt_comment = (Button) itemView.findViewById(R.id.bt_comment);

            img_main.setOnClickListener(this);
            bt_facebook.setOnClickListener(this);
            bt_twitter.setOnClickListener(this);
//            bt_comment.setOnClickListener(this);
            bt_share.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            /*if (v.getId() == bt_comment.getId()) {
                FeedDialogFragment newFragment = FeedDialogFragment.newInstance();
                newFragment.show(((AppCompatActivity)context).getSupportFragmentManager(),"Title");
                Toast.makeText(v.getContext(), "Comment  ", Toast.LENGTH_SHORT).show();

            } else*/ if (v.getId() == bt_facebook.getId()) {

                Toast.makeText(v.getContext(), "Facebook  ", Toast.LENGTH_SHORT).show();

            } else if (v.getId() == bt_twitter.getId()) {

                Toast.makeText(v.getContext(), "Twitter  ", Toast.LENGTH_SHORT).show();

            } else if (v.getId() == bt_share.getId()) {

                Toast.makeText(v.getContext(), "Share  ", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();

                FeedDialogFragment newFragment = FeedDialogFragment.newInstance();
                newFragment.setStyle( DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
                newFragment.show(((MainActivity)context).getSupportFragmentManager(),"Title");
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_row, parent, false);
        FeedHolder feedHolder = new FeedHolder(view);

        return feedHolder;
    }

    @Override
    public void onBindViewHolder(FeedHolder holder, int position) {

        holder.tv_title.setText(feedList.get(position).getTitle());

        // Here you apply the animation when the view is bound
        setAnimation(holder.img_main, position);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }


    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


}
