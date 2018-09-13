package com.lesson.mcm_pc.lesson2_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextButton(View v){

        EditText txt = findViewById(R.id.txtInput);
        Intent in = new Intent(this, NextActivity.class);
        in.putExtra("data", txt.getText().toString());
        startActivity(in);
    }
}
