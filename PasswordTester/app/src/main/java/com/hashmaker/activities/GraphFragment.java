package com.hashmaker.activities;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.hashmaker.R;
import com.hashmaker.data.Hash;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {
    private BarChart barChart;
    private ArrayList<Hash> hashes = new ArrayList<Hash>();

    //This is need to wrap the data
    private List<BarEntry> entries = new ArrayList<BarEntry>();

    public GraphFragment() {
        // Required empty public constructor
    }


    public GraphFragment(ArrayList<Hash> hashes) {
        this.hashes = hashes;
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
        for(int i = 0; i < hashes.size(); i++) {
            entries.add(new BarEntry(i, (float)hashes.get(i).getTime()));
        }


        BarDataSet dataSet = new BarDataSet(entries, "Time values in milliseconds");
     //   dataSet.setColor(1);
     //   dataSet.setValueTextColor(1);
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.invalidate();

        XAxis xAxis = barChart.getXAxis();
        //xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(5f);
        xAxis.setTextColor(Color.BLUE);




        styleGraph(barChart);

    }

    public void styleGraph(BarChart barChart) {
        Description description = new Description();
        description.setText("Hash/Time Graph");
        barChart.setDescription(description);
    }

    ValueFormatter formatter = new ValueFormatter() {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return hashes.get((int) value).getType();
        }
    };

}
