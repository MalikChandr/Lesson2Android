package com.lesson.mcm_pc.lesson2_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        TextView txt = findViewById(R.id.txtView);

        //Intent intent = getIntent();
        if (getIntent().hasExtra("data")){
            data = getIntent().getExtras().getString("data");
            txt.setText(data);
        }else{
            txt.setText("Data tidak ada");
        }

    }
}
