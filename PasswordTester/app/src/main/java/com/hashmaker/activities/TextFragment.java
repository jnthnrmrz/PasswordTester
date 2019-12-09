package com.hashmaker.activities;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmaker.R;
import com.hashmaker.data.Hash;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {

    private static final String TAG = "TextFragment";
    private ArrayList<Hash> hashes = new ArrayList<Hash>();



    public TextFragment() {
        // Required empty public constructor
    }

    public TextFragment(ArrayList<Hash> hashes) {
        this.hashes = hashes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_text, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), hashes);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        Log.i("TextFragment", "hashes " + hashes.get(0).toString());

        return view;
    }


}
