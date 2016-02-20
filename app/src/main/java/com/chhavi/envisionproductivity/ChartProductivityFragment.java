package com.chhavi.envisionproductivity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartProductivityFragment extends Fragment {

    PieChart mChartUpperPrimary;
    BarChart mChart;
    View v;



    public ChartProductivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_chart_productivity, container, false);
        mChartUpperPrimary = (PieChart) v.findViewById(R.id.chart1);
        // mChartPrimary = (PieChart)v.findViewById(R.id.chart2);
        mChartUpperPrimary.setUsePercentValues(true);
        mChartUpperPrimary.setDragDecelerationFrictionCoef(0.95f);

        mChartUpperPrimary.setDrawHoleEnabled(true);
        mChartUpperPrimary.setHoleColorTransparent(true);

        mChartUpperPrimary.setTransparentCircleColor(Color.WHITE);
        mChartUpperPrimary.setTransparentCircleAlpha(110);

        mChartUpperPrimary.setHoleRadius(58f);
        mChartUpperPrimary.setTransparentCircleRadius(61f);

        mChartUpperPrimary.setDrawCenterText(true);

        mChartUpperPrimary.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChartUpperPrimary.setRotationEnabled(true);

        mChartUpperPrimary.setCenterText("Your Productivity");
        mChartUpperPrimary.animateY(1500, Easing.EasingOption.EaseInOutQuad);
        setData(2, 200, mChartUpperPrimary, 0);

        Legend l = mChartUpperPrimary.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        seBarChart();

        return v;
    }

    private void seBarChart() {
        mChart = (BarChart) v.findViewById(R.id.chart2);
        //
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);
        //mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.animateY(2000);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);



        YAxis leftAxis = mChart.getAxisLeft();
        // leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(8, false);
        // leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        setDataForBarChart(5, 100);
    }

    private void setDataForBarChart(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        //for (int i = 0; i < count; i++) {
        xVals.add("Mails Read");
        xVals.add("Mails Undead");
        xVals.add("Steps Walked");
        xVals.add("Calories Burnt");
        // }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        float val = 12;
        yVals1.add(new BarEntry(val*170,0));
        yVals1.add(new BarEntry(val*80,1));
        yVals1.add(new BarEntry(val*150f,2));
        yVals1.add(new BarEntry(val*80,3));
     /*   for (int i = 0; i < 4; i++) {
           // float mult = (range + 1);
          //  float val = (float) (Math.random() * mult);
            yVals1.add(new BarEntry(val, i));
        }*/

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setBarSpacePercent(35f);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);
        ArrayList<Integer> colors = new ArrayList<Integer>();
       /* colors.add(R.color.purple_A400);
        colors.add(R.color.green_A400);
        colors.add(R.color.blue_500);
        colors.add(R.color.rea);*/



        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
//
        //      for (int c : ColorTemplate.JOYFUL_COLORS)
        //        colors.add(c);
//
        //for (int c : ColorTemplate.COLORFUL_COLORS)
        //    colors.add(R.color.material_blue_grey_800);
//
        //      for (int c : ColorTemplate.LIBERTY_COLORS)
        //         colors.add(c);
//
        //  for (int c : ColorTemplate.PASTEL_COLORS)
        //    colors.add(c);

//      /  colors.add(ColorTemplate.getHoloBlue());

        //dataSets.setColors(colors);

        BarData data = new BarData(xVals, set1);

        data.setValueTextSize(10f);
        //      data.setValueTypeface(mTf);

        mChart.setData(data);
    }

    private void setData(int count, float range, PieChart chart, int ca) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        switch (ca){
            case 0:
                //yVals1.add(new Entry());
                yVals1.add(new Entry(420f,0));
                yVals1.add(new Entry(40f,1));

                break;
            case 1:
                yVals1.add(new Entry(150f,0));
                yVals1.add(new Entry(30f,1));



        }



        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(0,"Productive");
        xVals.add(1,"Not Productive");
;

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
//        dataSet.setColors(new int[] { R.color.sticky1, R.color.sticky2,R.color.sticky3});


        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
//        colors.add(R.color.sticky1);
//        colors.add(R.color.sticky2);
//        colors.add(R.color.sticky3);
//        colors.add(R.color.sticky4);




        //      for (int c : ColorTemplate.JOYFUL_COLORS)
        //        colors.add(c);
//
        //for (int c : ColorTemplate.COLORFUL_COLORS)
        //    colors.add(R.color.material_blue_grey_800);
//
        //      for (int c : ColorTemplate.LIBERTY_COLORS)
        //         colors.add(c);
//
          for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        switch (ca){
            case 0:
                for (int c : ColorTemplate.VORDIPLOM_COLORS)
                    colors.add(c);
                colors.add(ColorTemplate.getHoloBlue());
                break;
            case 1:
                for (int c : ColorTemplate.COLORFUL_COLORS)
                    colors.add(c);
                break;

        }



        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);
        // data.setValueTypeface(tf);

        switch (ca){
            case 0:
                mChartUpperPrimary.setData(data);

                // undo all highlights
                mChartUpperPrimary.highlightValues(null);

                mChartUpperPrimary.invalidate();
                break;
            case 1:

                break;

        }

    }

}
