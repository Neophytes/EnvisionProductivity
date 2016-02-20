package com.chhavi.envisionproductivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import link.fls.SwipeStack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chhavi on 19/2/16.
 */
public class DashboardActivity extends AppCompatActivity {

    SwipeStackAdapter adapter;
    List<KeyPair> rescueTime;
    ProgressBar progressBar;
    SwipeStack swipeStack3;
    static final String API_KEY = "B63LMGbNX0OCUfwOOFEU_n2S3aDNrEbQeZVQlBXM";
    static final String API_URL = "https://www.rescuetime.com/anapi/data?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        new RetrieveFeedTask().execute();

        List<KeyPair> fitness = new ArrayList<KeyPair>();
        fitness = (ArrayList<KeyPair>)getIntent().getSerializableExtra("Fitness");

       rescueTime  = new ArrayList<KeyPair>();
     //   fitness = (ArrayList<KeyPair>)getIntent().getSerializableExtra("Fitness");

        List<KeyPair> gmailLabel = new ArrayList<KeyPair>();
        gmailLabel.add(new KeyPair("Messages Unread", "23"));
        gmailLabel.add(new KeyPair("Messages To be replied", "52"));
        gmailLabel.add(new KeyPair("Total Messages", "113"));
     //   gmailLabel = (ArrayList<KeyPair>)getIntent().getSerializableExtra("gmailLabel");


     //   FitnessActivity fitnessActivity = FitnessActivity.getSingletonObject();
     //   ArrayList<KeyPair> fitnessPair = fitnessActivity.fitnessPairs;
        //Log.e("run", fitness.get(0).getValue());

        List<String> mData1 = new ArrayList<>();
        for(int i=0;i<10;i++)
            mData1.add("1 Put Your Card Data Here :) ");
        SwipeStack swipeStack1 = (SwipeStack) findViewById(R.id.swipeStack1);
        swipeStack1.setAdapter(new SwipeStackAdapter(DashboardActivity.this,fitness, getResources().getColor(R.color.sticky1)));

        List<String> mData2 = new ArrayList<>();
        for(int i=0;i<10;i++)
            mData2.add("2 Put Your Card Data Here :) ");
        SwipeStack swipeStack2 = (SwipeStack) findViewById(R.id.swipeStack2);
        swipeStack2.setBackgroundColor(getResources().getColor(R.color.sticky2));
        swipeStack2.setAdapter(new SwipeStackAdapter(DashboardActivity.this,gmailLabel, getResources().getColor(R.color.sticky2)));

        List<String> mData3 = new ArrayList<>();
        for(int i=0;i<10;i++)
            mData3.add("3 Put Your Card Data Here :) ");

        adapter = new SwipeStackAdapter(DashboardActivity.this, rescueTime, getResources().getColor(R.color.sticky3));
            swipeStack3 = (SwipeStack) findViewById(R.id.swipeStack3);
        swipeStack3.setBackgroundColor(getResources().getColor(R.color.sticky3));
        swipeStack3.setAdapter(adapter);

        List<String> mData4 = new ArrayList<>();
        for(int i=0;i<10;i++)
            mData4.add("4 Put Your Card Data Here :) ");
        SwipeStack swipeStack4 = (SwipeStack) findViewById(R.id.swipeStack4);
        swipeStack4.setBackgroundColor(getResources().getColor(R.color.sticky4));
        swipeStack4.setAdapter(new SwipeStackAdapter(DashboardActivity.this,fitness, getResources().getColor(R.color.sticky4)));

    }


    public class SwipeStackAdapter extends BaseAdapter {

        private List<KeyPair> mData;
        private Context context;
        private int color;

        public SwipeStackAdapter(Context context, List<KeyPair> data, int color) {
            this.mData = data;
            this.context = context;
            this.color = color;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public KeyPair getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
            TextView textHeader = (TextView) convertView.findViewById(R.id.header);
            TextView text = (TextView) convertView.findViewById(R.id.text);
            LinearLayout cardLayout = (LinearLayout)convertView.findViewById(R.id.card_background);
            cardLayout.setBackgroundColor(color);
            textHeader.setText(mData.get(position).getName());
            text.setText(mData.get(position).getValue());

           // FitnessActivity.
            return convertView;
        }
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
                    Log.i("JSON", bufferedReader.readLine());
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } catch (Exception e){
                    Log.e("Err", e.getMessage());
                    return null;
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


                rescueTime.add(new KeyPair("Date", Date.get(0)));
                rescueTime.add(new KeyPair("Time Spent", TimeSpent.get(0).toString()));
                rescueTime.add(new KeyPair("Productivity", Productivity.get(0).toString()));
                adapter = new SwipeStackAdapter(DashboardActivity.this, rescueTime, getResources().getColor(R.color.sticky3));
                swipeStack3.setAdapter(adapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
