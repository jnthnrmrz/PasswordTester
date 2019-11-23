package com.passwordtester.activities;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

import com.passwordtester.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final String EXTRA_PASSWORD = "com.passwordtester.activities.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    /**
     * Called when user presses create hash button
     * @param view button
     */
    public void createHash(View view) {
        Intent intent = new Intent(this, DisplayHashActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String password = editText.getText().toString();
        intent.putExtra(EXTRA_PASSWORD, password);
        startActivity(intent);
    }
}
