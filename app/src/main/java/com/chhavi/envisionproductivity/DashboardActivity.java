package com.chhavi.envisionproductivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import link.fls.SwipeStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chhavi on 19/2/16.
 */
public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);


        List<String> mData = new ArrayList<>();
        for(int i=0;i<10;i++)
            mData.add("asd");
        SwipeStack swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        swipeStack.setAdapter(new SwipeStackAdapter(DashboardActivity.this,mData));
    }

    public class SwipeStackAdapter extends BaseAdapter {

        private List<String> mData;
        private Context context;

        public SwipeStackAdapter(Context context, List<String> data) {
            this.mData = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
            TextView textViewCard = (TextView) convertView.findViewById(R.id.textViewCard);
            textViewCard.setText(mData.get(position));

            return convertView;
        }
    }
}
