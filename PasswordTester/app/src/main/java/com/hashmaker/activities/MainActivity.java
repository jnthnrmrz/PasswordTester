package com.hashmaker.activities;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.hashmaker.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final String EXTRA_PASSWORD = "com.passwordtester.activities.PASSWORD";
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Hash Maker");

    }

    /**
     * Called when user presses create hash button
     * @param view button
     */
    public void createHash(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String password = editText.getText().toString();

        if(password.equals(null) || password.equals("")) {
            Toast.makeText(mContext, "Cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, DisplayHashActivity.class);
            intent.putExtra(EXTRA_PASSWORD, password);
            startActivity(intent);
        }
    }
}
