package com.bicubic.tennis.adapter;

import android.content.Context;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bicubic.tennis.R;
import com.bicubic.tennis.activity.MainActivity;
import com.bicubic.tennis.fragment.PlayerDialogFragment;
import com.bicubic.tennis.model.Player;

import java.util.List;

/**
 * Created by admin on 8/6/2016.
 */
public class RVPlayersAdapter extends RecyclerView.Adapter<RVPlayersAdapter.PlayerHolder> {

    List<Player> playerList;
    static Context context;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public RVPlayersAdapter(List<Player> playerList, Context context) {
        this.playerList = playerList;
        this.context = context;
    }

    @Override
    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row,parent,false);
        PlayerHolder playerHolder = new PlayerHolder(view);

        return playerHolder;
    }

    @Override
    public void onBindViewHolder(PlayerHolder holder, int position) {

        holder.tv_name.setText(playerList.get(position).getName());


        // Here you apply the animation when the view is bound
        setAnimation(holder.lin_main, position);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }


    public static class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_name;
        ImageView img_player;
        LinearLayout lin_main;

        public PlayerHolder(View itemView) {
            super(itemView);

            lin_main = (LinearLayout)itemView.findViewById(R.id.lin_main);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            img_player = (ImageView)itemView.findViewById(R.id.img_player);

            lin_main.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            PlayerDialogFragment newFragment = PlayerDialogFragment.newInstance();
            newFragment.setStyle( DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
            newFragment.show(((MainActivity)context).getSupportFragmentManager(),"Title");

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
