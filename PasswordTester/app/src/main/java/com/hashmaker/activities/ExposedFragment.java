package com.hashmaker.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hashmaker.R;


public class ExposedFragment extends Fragment {
    private static final String TAG = "ExposedFragment";
    private int HASH_INDEX = 0;
    private int TIMES_EXPOSED_INDEX = 1;

    private String[] exposedHash;
    public ExposedFragment() {}

    public ExposedFragment(String[] exposedHash) {
        this.exposedHash = exposedHash;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exposed, container, false);
        Log.i(TAG, "expo: " + exposedHash[1]);

        TextView textView = (TextView) view.findViewById(R.id.textView);

        // TextView textView = (TextView) getView().findViewById(R.id.textView);
        if (exposedHash[HASH_INDEX].equals("ERROR")) {
            textView.setText("Whoops there was an error please try again");
        } else if (exposedHash[TIMES_EXPOSED_INDEX].equals("0")) {
            textView.setText("Your password has not been found in a data breach");
        } else {
            String s = "Your password has been exposed! \n" +
                    "It has been found a total of " + "<font color='#FF0000'>" + exposedHash[TIMES_EXPOSED_INDEX] + "</font>"
                     + " different times";
            textView.setText(Html.fromHtml(s));


        }

        return view;
    }
}
