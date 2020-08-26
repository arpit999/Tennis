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
import com.bicubic.tennis.adapter.RVFeedAdapter;
import com.bicubic.tennis.model.Feed;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    private List<Feed> feedList;
    private RecyclerView rv;
    View rootView;

    public FeedFragment() {
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

        feedList = new ArrayList<>();

        feedList.add(new Feed("Rio Olympics 2016: 24 hours in 24 pictures - Friday 5 August 2016"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Story of the local hero coach going to Rio thanks to crowdfunding shows the real soul of Olympic Games"));
        feedList.add(new Feed("Comment: Run rates are up and crowds are down: there are many reasons four-day Test matches are a good idea"));
        feedList.add(new Feed("Tom Watson says Shami Chakrabarti peerage is a 'mistake' as he reveals he was not consulted on decision"));
        feedList.add(new Feed("Cargo plane crashes onto busy Italian road after overshooting runway"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));
        feedList.add(new Feed("Rio 2016 Olympics: Integrity of Games in tatters as IOC clears more than two-thirds of Russia team"));

    }


    private void initializeAdapter(){
        RVFeedAdapter adapter = new RVFeedAdapter(feedList,getActivity());
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
