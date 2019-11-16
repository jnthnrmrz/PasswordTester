package com.passwordtester.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.passwordtester.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_PASSWORD = "com.passwordtester.activities.PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
