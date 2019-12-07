package com.passwordtester.activities;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.passwordtester.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {

    private static final String TAG = "TextFragment";

    public TextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "TextFragment: OnCreateView");

        View view = inflater.inflate(R.layout.fragment_text, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        // Inflate the layout for this fragment
        return view;
    }

}
