package com.bicubic.tennis.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bicubic.tennis.R;
import com.bicubic.tennis.model.Rank;

import java.util.List;

/**
 * Created by admin on 8/6/2016.
 */
public class RVRankingAdapter extends RecyclerView.Adapter<RVRankingAdapter.RankHolder> {

    List<Rank> rankList;
    private Context context;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public RVRankingAdapter(List<Rank> rankList, Context context) {
        this.rankList = rankList;
        this.context = context;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RankHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_row, parent, false);
        RankHolder rankHolder = new RankHolder(view);


        return rankHolder;
    }

    @Override
    public void onBindViewHolder(RankHolder holder, int position) {

        holder.tv_no.setText(rankList.get(position).getNo());
        holder.tv_name.setText(rankList.get(position).getName());
        holder.tv_points.setText(rankList.get(position).getPoints());

        // Here you apply the animation when the view is bound
        setAnimation(holder.lin_main, position);

    }

    @Override
    public int getItemCount() {
        return rankList.size();
    }

    public static class RankHolder extends RecyclerView.ViewHolder {

        TextView tv_no, tv_name, tv_points;
        LinearLayout lin_main;

        public RankHolder(View itemView) {
            super(itemView);

            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_points = (TextView) itemView.findViewById(R.id.tv_points);
            lin_main = (LinearLayout)itemView.findViewById(R.id.lin_main);


        }
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
