package com.hashmaker.activities;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.hashmaker.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    public static final String EXTRA_PASSWORD = "com.passwordtester.activities.PASSWORD";
    Context mContext = this;
    private String password;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Hash Maker");

        CheckBox box = (CheckBox)findViewById(R.id.checkBox1);
        editText = (EditText) findViewById(R.id.editText);

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else {
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    /**
     * Called when user presses create hash button
     * @param view button
     */
    public void createHash(View view) {
        editText = (EditText) findViewById(R.id.editText);
        password = editText.getText().toString();

        Log.i(TAG, "Password: " + password);

        if(password.equals(null) || password.equals("")) {
            Toast.makeText(mContext, "Cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, DisplayHashActivity.class);
            intent.putExtra(EXTRA_PASSWORD, password);
            startActivity(intent);
        }
    }
}
