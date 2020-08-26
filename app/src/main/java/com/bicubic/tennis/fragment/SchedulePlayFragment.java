package com.bicubic.tennis.fragment;

import android.app.Activity;
import android.graphics.LightingColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bicubic.tennis.R;
import com.bicubic.tennis.model.SchedulePlay;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SchedulePlayFragment extends Fragment {

    private static final String TAG = "SheduleFrag";
    private ProgressBar progressBar;
    ArrayList<SchedulePlay> schedulePlayList;

    public SchedulePlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, 0x81C784));

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
                    .url("http://data2.scorespro.com/exporter/json.php?state=clientUpdate&usr=16lewasmns&pwd=FCv67RTwQ&type=2&s=2")
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

            Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
            String current_date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(now);

//            Log.d("", "onPostExecute: date "+year+"-"+month+"-"+day);
            Log.d("", "onPostExecute: date " + current_date);

            if (responseString != null) {

                try {
                    schedulePlayList = new ArrayList<SchedulePlay>();
                    String tournament = null, starttime = null, home_player = null, home_player_point = null, away_player = null, away_player_point = null;

                    JSONObject jObject = new JSONObject(responseString).getJSONObject("list").getJSONObject("Sport").getJSONObject("2").getJSONObject("Matchday").getJSONObject(current_date).getJSONObject("Match");

                    Log.d("", "onPostExecute: jObject = " + jObject);

                    Iterator<String> keys = jObject.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        Log.v("match number key", key);

                        if (jObject.get(key) instanceof JSONObject) {

                            JSONObject innerJObject = jObject.getJSONObject(key);
                            Log.d(TAG, "onPostExecute: innerJObject = "+innerJObject);

                            Iterator<String> key_match_no = jObject.keys();

                            while (key_match_no.hasNext()) {
                                String key_inner = key_match_no.next();

                                if (innerJObject.get(key_inner) instanceof JSONObject) {

                                }else if (jObject.get(key) instanceof String) {
                                    String value = jObject.getString("type");
                                    Log.v("string key = type", "value = " + value);

                                }

                            }
                            /*if (jObject.get(key) == "Home") {
                                JSONObject homeObj = jObject.getJSONObject(key);
                                home_player = homeObj.getString("name");
                                Log.d("", "onPostExecute: home player = " + home_player);
                            }
                            if (jObject.get(key) == "Away") {
                                JSONObject awayObj = jObject.getJSONObject(key);
                                away_player = awayObj.getString("name");
                                Log.d("", "onPostExecute: away player = " + away_player);
                            }*/

                        } else if (jObject.get(key) instanceof String) {
                            String value = jObject.getString("type");
                            Log.v("string key = type", "value = " + value);

                        }

                    }
                    /*Iterator<String> keys = jObject.keys();
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


                        } else if (jObject.get(key) instanceof String){
                            String value = jObject.getString("type");
                            Log.v("key = type", "value = " + value);
                        }
                    }*/

                    if (schedulePlayList != null) {

                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Couldn't get Data from server", Toast.LENGTH_SHORT).show();
            }

        }
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
