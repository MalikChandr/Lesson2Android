package com.lesson.mcm_pc.lesson3listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String data = getIntent().getExtras().getString("data");
        TextView txt = findViewById(R.id.txtGet);
        txt.setText(data);
    }
}
