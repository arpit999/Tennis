package com.bicubic.tennis.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bicubic.tennis.R;
import com.bicubic.tennis.adapter.RVStatisticAdapter;
import com.bicubic.tennis.model.Statistic;

import java.util.ArrayList;
import java.util.List;


public class StatisticsFragment extends Fragment {

    RecyclerView rv_statistics;
    View rootView;
    private List<Statistic> statisticList;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_statistics, container, false);

        rv_statistics = (RecyclerView)rootView. findViewById(R.id.rv_statistics);

        initializeData();

        RVStatisticAdapter rvStatisticAdapter = new RVStatisticAdapter(statisticList,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        rv_statistics.setLayoutManager(linearLayoutManager);
        rv_statistics.setItemAnimator(new DefaultItemAnimator());
        rv_statistics.setAdapter(rvStatisticAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    private void initializeData() {

        statisticList = new ArrayList<>();

        statisticList.add(new Statistic("1","Player 1",null,"52",Statistic.ONE_ROW));
        statisticList.add(new Statistic("2","Player 1","Player 2","48",Statistic.TWO_ROW));
        statisticList.add(new Statistic("3","Player 3","Player 4","45",Statistic.TWO_ROW));
        statisticList.add(new Statistic("4","Player 1",null,"40",Statistic.ONE_ROW));
        statisticList.add(new Statistic("5","Player 1","Player 5","30",Statistic.TWO_ROW));
        statisticList.add(new Statistic("6","Player 1",null,"25",Statistic.ONE_ROW));
        statisticList.add(new Statistic("7","Player 1",null,"23",Statistic.ONE_ROW));
        statisticList.add(new Statistic("8","Player 1",null,"22",Statistic.ONE_ROW));
        statisticList.add(new Statistic("9","Player 1","Player 6","20",Statistic.TWO_ROW));
        statisticList.add(new Statistic("10","Player 1",null,"19",Statistic.ONE_ROW));
        statisticList.add(new Statistic("11","Player 1",null,"18",Statistic.ONE_ROW));
        statisticList.add(new Statistic("12","Player 1","Player 7","17",Statistic.TWO_ROW));

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
