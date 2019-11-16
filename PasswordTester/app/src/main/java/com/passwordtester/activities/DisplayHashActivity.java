package com.passwordtester.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.passwordtester.R;

public class DisplayHashActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_hash);

        Intent intent = getIntent();
        String password = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);

        //Add scrollable text
        textView = findViewById(R.id.hashValue1);
    }

    public String displayHash(String hash) {
        return hash;
    }

    public String displayTime() {
        return null;
    }


}
