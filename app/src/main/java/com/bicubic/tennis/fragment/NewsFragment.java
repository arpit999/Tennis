package com.bicubic.tennis.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bicubic.tennis.R;
import com.bicubic.tennis.adapter.RVNewsAdapter;
import com.bicubic.tennis.model.News;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {

    private List<News> newsList;
    private RecyclerView rv;
    View rootView;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();

        initializeAdapter();


        return rootView;
    }

    private void initializeData() {

        newsList = new ArrayList<>();

        newsList.add(new News("Roger uses crowd to help him beat Tsonga at Wimbledon  "));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Story of the local hero coach going to Rio thanks to crowdfunding shows the real soul of Olympic Games"));
        newsList.add(new News("Comment: Run rates are up and crowds are down: there are many reasons four-day Test matches are a good idea"));
        newsList.add(new News("Tom Watson says Shami Chakrabarti peerage is a 'mistake' as he reveals he was not consulted on decision"));
        newsList.add(new News("Cargo plane crashes onto busy Italian road after overshooting runway"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Roger uses crowd to help him beat Tsonga at Wimbledon  "));
        newsList.add(new News("Roger uses crowd to help him beat Tsonga at Wimbledon  "));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        newsList.add(new News("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));


    }


    private void initializeAdapter(){
        RVNewsAdapter adapter = new RVNewsAdapter(newsList,getActivity());
        rv.setAdapter(adapter);
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
