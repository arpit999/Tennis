package com.bicubic.tennis.fragment;

import android.graphics.LightingColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bicubic.tennis.R;
import com.bicubic.tennis.adapter.RVRankingAdapter;
import com.bicubic.tennis.model.Rank;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RankingFragment extends Fragment {

    private List<Rank> rankList;
    private RecyclerView rv_ranking;
    View rootView;
    private ProgressBar progressBar;

    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ranking, container, false);

        rv_ranking=(RecyclerView)rootView.findViewById(R.id.rv_ranking);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, 0x81C784));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv_ranking.setLayoutManager(llm);
        rv_ranking.setHasFixedSize(true);

        new GetRankingData().execute();

        // Inflate the layout for this fragment
        return rootView;
    }

    public class GetRankingData extends AsyncTask<String, Void, Void> {

        String responseString;
        Response response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... str) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://data2.scorespro.com/exporter/json.php?state=clientStructure&type=19")
                    .build();

            try {

                response = client.newCall(request).execute();
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                {
                    responseString = response.body().string();
                    System.out.println(responseString);
                    response.body().close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (responseString != null) {

                try {
                    rankList = new ArrayList<Rank>();
                    JSONObject jObject = new JSONObject(responseString).getJSONObject("list");
                    Iterator<String> keys = jObject.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        Log.v("list key", key);
                        if(jObject.get(key) instanceof JSONObject) {
                            JSONObject innerJObject = jObject.getJSONObject(key);
                            String id = innerJObject.getString("id");
                            String name = innerJObject.getString("name");
                            String points = innerJObject.getString("points");
                            String ranking = innerJObject.getString("ranking");
                            String tour = innerJObject.getString("tour");
                            String lastUpdate = innerJObject.getString("lastUpdate");
                            Log.v("details", "id = " + id + ", " + "name = " + name + ", " + "points = " + points + ", " + "ranking = " + ranking + ", " + "tour = " + tour + ", " + "lastUpdate = " + lastUpdate);

                            rankList.add(new Rank(ranking,name,points));

                        } else if (jObject.get(key) instanceof String){
                            String value = jObject.getString("type");
                            Log.v("key = type", "value = " + value);
                        }
                    }

                    if (rankList!=null){

                        RVRankingAdapter adapter = new RVRankingAdapter(rankList,getActivity());
                        rv_ranking.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);

                    }else{
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }else{
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Couldn't get Data from server", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
