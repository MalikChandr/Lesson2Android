package id.lesson.malik.bukalapaktraining;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView txt = findViewById(R.id.txtJudul);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/FORTE.TTF");
        txt.setTypeface(font);

    }

    public void onClickBarang(View v){
        Intent i = new Intent(this, LihatBarangActivity.class);
        startActivity(i);
    }

    public void onClickTroli(View v){
        Intent i = new Intent(this, TroliActivity.class);
        startActivity(i);
    }
}
