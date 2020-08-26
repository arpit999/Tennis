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
import com.bicubic.tennis.model.Statistic;

import java.util.List;

import static com.bicubic.tennis.model.Statistic.ONE_ROW;
import static com.bicubic.tennis.model.Statistic.TWO_ROW;

/**
 * Created by admin on 8/9/2016.
 */
public class RVStatisticAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Statistic> statisticList;
    Context context;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public RVStatisticAdapter(List<Statistic> statisticList, Context context) {
        this.statisticList = statisticList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {

            case ONE_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_row_one, parent, false);
                return new OneRowHolder(view);
            case TWO_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_row_two, parent, false);
                return new TwoRowHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Statistic object = statisticList.get(position);
        if (object != null) {
            switch (object.getRow_type()) {
                case ONE_ROW:
                    ((OneRowHolder) holder).tv_no.setText(object.getNo());
                    ((OneRowHolder) holder).tv_player_one.setText(object.getPlayer_one());
                    ((OneRowHolder) holder).tv_title.setText(object.getTitle());
                    // Here you apply the animation when the view is bound
                    setAnimation(((OneRowHolder) holder).lin_main, position);
                    break;
                case TWO_ROW:
                    ((TwoRowHolder) holder).tv_no.setText(object.getNo());
                    ((TwoRowHolder) holder).tv_player_one.setText(object.getPlayer_one());
                    ((TwoRowHolder) holder).tv_player_two.setText(object.getPlayer_two());
                    ((TwoRowHolder) holder).tv_title.setText(object.getTitle());
                    setAnimation(((TwoRowHolder) holder).lin_main, position);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (statisticList == null)
            return 0;
        return statisticList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (statisticList != null) {
            Statistic object = statisticList.get(position);
            if (object != null) {
                return object.getRow_type();
            }
        }
        return 0;
    }

    public static class OneRowHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_no, tv_player_one;
        private LinearLayout lin_main;

        public OneRowHolder(View itemView) {
            super(itemView);
            lin_main = (LinearLayout)itemView.findViewById(R.id.lin_main);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_player_one = (TextView) itemView.findViewById(R.id.tv_player_one);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);

        }
    }

    public static class TwoRowHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_no, tv_player_one, tv_player_two;
        private LinearLayout lin_main;

        public TwoRowHolder(View itemView) {
            super(itemView);
            lin_main = (LinearLayout)itemView.findViewById(R.id.lin_main);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_player_one = (TextView) itemView.findViewById(R.id.tv_player_one);
            tv_player_two = (TextView) itemView.findViewById(R.id.tv_player_two);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
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
