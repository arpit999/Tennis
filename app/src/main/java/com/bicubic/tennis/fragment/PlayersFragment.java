package com.bicubic.tennis.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bicubic.tennis.R;
import com.bicubic.tennis.adapter.RVPlayersAdapter;
import com.bicubic.tennis.model.Player;

import java.util.ArrayList;
import java.util.List;


public class PlayersFragment extends Fragment {

    View rootView;
    private List<Player> playerList;
    private RecyclerView rv_player;
    Context context;

    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_players, container, false);


        rv_player = (RecyclerView) rootView.findViewById(R.id.rv_player);

//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
//        rv_player.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        // use this setting to improve performance if you know that changes
// in content do not change the layout size of the RecyclerView
        rv_player.setHasFixedSize(true);
// first param is context and second param is spanCount ie.,
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

// use a grid layout manager to show items like gridview
        rv_player.setLayoutManager(glm);


        initializeData();

        initializeAdapter();

       /* rv_player.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(getActivity(), "Player "+(position+1), Toast.LENGTH_SHORT).show();

                    }
                })
        );*/


        return rootView;
    }


    private void initializeData() {

        playerList = new ArrayList<Player>();

        playerList.add(new Player("Player 1"));
        playerList.add(new Player("Player 2"));
        playerList.add(new Player("Player 3"));
        playerList.add(new Player("Player 4"));
        playerList.add(new Player("Player 5"));
        playerList.add(new Player("Player 6"));
        playerList.add(new Player("Player 7"));
        playerList.add(new Player("Player 8"));

    }


    private void initializeAdapter() {
        RVPlayersAdapter adapter = new RVPlayersAdapter(playerList, getActivity());
        rv_player.setAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
