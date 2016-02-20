package com.chhavi.envisionproductivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chhavi on 20/2/16.
 */
public class SwipeStackAdapter extends BaseAdapter {

    private List<KeyPair> mData;
    private Context context;
    private int color;
    private int resourceID;

    public SwipeStackAdapter(Context context, List<KeyPair> data, int color, int resourceID) {
        this.mData = data;
        this.context = context;
        this.color = color;
        this.resourceID = resourceID;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        convertView = inflater.inflate(R.layout.card, parent, false);
        TextView textHeader = (TextView) convertView.findViewById(R.id.header);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
        LinearLayout cardLayout = (LinearLayout)convertView.findViewById(R.id.card_background);
        cardLayout.setBackgroundColor(color);
        textHeader.setText(mData.get(position).getName());
        text.setText(mData.get(position).getValue());
        icon.setImageResource(resourceID);

        // FitnessActivity.
        return convertView;
    }
}