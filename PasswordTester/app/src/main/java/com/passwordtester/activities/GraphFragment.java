package com.passwordtester.activities;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.passwordtester.R;
import com.passwordtester.data.Hash;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {
    private BarChart barChart;
    //Hash[] data;

    //This is need to wrap the data
    private List<BarEntry> entries = new ArrayList<BarEntry>();

    public GraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_graph, container, false);
        barChart = view.findViewById(R.id.hashGraph);
        createGraph(barChart);
        return view;
    }

    public void createGraph(BarChart barChart) {
        /*for(int i = 0; i < entries.size(); i++) {
            entries.add(new BarEntry(10, 10));
        }*/
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1, 80f));
        entries.add(new BarEntry(2f, 70f));

        //You need anoother list for y-axis
        ArrayList<String> testString = new ArrayList<String>();

        testString.add("Monday");
        testString.add("Tuesday");
        BarDataSet dataSet = new BarDataSet(entries, "Time values");
     //   dataSet.setColor(1);
     //   dataSet.setValueTextColor(1);
        BarData barData = new BarData(dataSet, dataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.invalidate();

        styleGraph(barChart);

    }

    public void styleGraph(BarChart barChart) {
        Description description = new Description();
        description.setText("Hash/Time Graph");
        barChart.setDescription(description);
    }

}
