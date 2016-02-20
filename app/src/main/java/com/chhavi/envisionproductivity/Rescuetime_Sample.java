package com.chhavi.envisionproductivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Rescuetime_Sample extends AppCompatActivity {

    TextView responseView;
    ProgressBar progressBar;
    static final String API_KEY = "B63LMGbNX0OCUfwOOFEU_n2S3aDNrEbQeZVQlBXM";
    static final String API_URL = "https://www.rescuetime.com/anapi/data?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rescuetime_sample_layout);

        responseView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button queryButton = (Button) findViewById(R.id.button);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
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
            progressBar.setVisibility(View.GONE);
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

            } catch (JSONException e) {
                e.printStackTrace();
            }

            responseView.setText(response);

        }
    }
}