package com.passwordtester.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.passwordtester.R;
import com.passwordtester.data.Hash;

import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<String> hashType = new ArrayList<String>();
    ArrayList<String> hashValue = new ArrayList<String>();
    Context mContext;

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "RecyclerViewAdapter: OnBindViewHolder");

        final String text = (String) holder.hashValue.getText(); //must be final for copy

        //Copies the hash to clipboard when viewholder is clicked
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked");

                ClipboardManager clipboard = (ClipboardManager)mContext.getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Hash value", text);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(mContext, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 11;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hashType;
        TextView hashValue;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hashType = itemView.findViewById(R.id.hashType);
            hashValue = itemView.findViewById(R.id.hashValue);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}

