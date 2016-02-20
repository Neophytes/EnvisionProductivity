package com.chhavi.envisionproductivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import link.fls.SwipeStack;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }

    static final String API_KEY = "B63LMGbNX0OCUfwOOFEU_n2S3aDNrEbQeZVQlBXM";
    static final String API_URL = "https://www.rescuetime.com/anapi/data?";
    ArrayList<KeyPair> rescueTime;
    SwipeStack swipeStack3;
    SwipeStackAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertview =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        new RetrieveFeedTask().execute();


        List<KeyPair> events = new ArrayList<KeyPair>();
        events.add(new KeyPair("Upcoming Birthday", "Kapil\n 20/02/16"));
        events.add(new KeyPair("Upcoming Event", "Social Hack Eve \n 3/03/16"));
        events.add(new KeyPair("Upcoming Holiday", "Holi \n 23/03/16"));
        rescueTime = new ArrayList<>();
        SwipeStack swipeStack1 = (SwipeStack) convertview.findViewById(R.id.swipeStack1);
         swipeStack3 = (SwipeStack) convertview.findViewById(R.id.swipeStack3);
        rescueTime.add(new KeyPair("Please Wait", "We are calculating your productivity"));
        adapter = new SwipeStackAdapter(getActivity(), rescueTime, getResources().getColor(R.color.sticky3));
        swipeStack3.setAdapter(adapter);
        ArrayList<KeyPair> fitness  = (ArrayList<KeyPair>) getArguments().getSerializable("Fitness");

        swipeStack1.setAdapter(new SwipeStackAdapter(getActivity(), fitness, getResources().getColor(R.color.sticky1)));


        List<KeyPair> gmailLabel = new ArrayList<KeyPair>();
        gmailLabel.add(new KeyPair("Messages Unread", "23"));
        gmailLabel.add(new KeyPair("Messages To be replied", "52"));
        gmailLabel.add(new KeyPair("Total Messages", "113"));
        SwipeStack swipeStack2 = (SwipeStack) convertview.findViewById(R.id.swipeStack2);
        swipeStack2.setAdapter(new SwipeStackAdapter(getActivity(), gmailLabel, getResources().getColor(R.color.sticky2)));

        SwipeStack swipeStack4 = (SwipeStack) convertview.findViewById(R.id.swipeStack4);
        swipeStack4.setAdapter(new SwipeStackAdapter(getActivity(),events, getResources().getColor(R.color.sticky4)));


        return convertview;
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {

            //         progressBar.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(Void... urls) {
            //String email = editText.getText().toString();
            // Do some validation here

            try {
                URL url = new URL(API_URL + "key=" + API_KEY + "&perspective=interval&restrict_kind=productivity&interval=hour&restrict_begin=2014-11-01&restrict_end=2016-02-20&format=json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line = "lol";
                    line = bufferedReader.readLine();
                    return line;
//                    Log.i("JSON", bufferedReader.readLine());
//                    StringBuilder stringBuilder = new StringBuilder();
//                    Log.i("LINE", line);
//                    while (line != null) {
//                        stringBuilder.append(line).append("\n");
//                    }
//                    bufferedReader.close();
//                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }

            // progressBar.setVisibility(View.GONE);

            Log.i("INFO", response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray header = jsonObject.getJSONArray("row_headers");
                JSONArray rows = jsonObject.getJSONArray("rows");
                ArrayList<String> Date = new ArrayList<>();
                ArrayList<Integer> TimeSpent = new ArrayList<>();
                ArrayList<Integer> NumberofPeople = new ArrayList<>();
                ArrayList<Integer> Productivity = new ArrayList<>();
                for(int i = 0;i< rows.length(); i++){
                    JSONArray data = rows.getJSONArray(i);
                    Date.add(data.getString(0));
                    TimeSpent.add(data.getInt(1));
                    NumberofPeople.add(data.getInt(2));
                    Productivity.add(data.getInt(3));
                }


                rescueTime.clear();
                swipeStack3.resetStack();
                rescueTime.add(new KeyPair("Date", Date.get(0)));
                rescueTime.add(new KeyPair("Time Spent", TimeSpent.get(0).toString()));
                rescueTime.add(new KeyPair("Productivity", Productivity.get(0).toString()));
               /* adapter = new SwipeStackAdapter(getActivity(), rescueTime, getResources().getColor(R.color.sticky3));
                swipeStack3.setAdapter(adapter);*/
                adapter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
