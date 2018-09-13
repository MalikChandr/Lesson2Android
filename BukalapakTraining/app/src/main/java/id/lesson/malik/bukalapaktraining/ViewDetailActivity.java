package id.lesson.malik.bukalapaktraining;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewDetailActivity extends AppCompatActivity {
    String nama, harga, deskripsi, nameActivity;
    int no;
    Integer[] imgList = {
            R.mipmap.drone,
            R.mipmap.headset,
            R.mipmap.hp,
            R.mipmap.jam,
            R.mipmap.keyboard,
            R.mipmap.laptop,
            R.mipmap.mouse,
            R.mipmap.sepatu,
            R.mipmap.speaker,
            R.mipmap.tabpen,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        no          = getIntent().getExtras().getInt("data_no");
        nama        = getIntent().getExtras().getString("data_nama");
        harga       = getIntent().getExtras().getString("data_harga");
        deskripsi   = getIntent().getExtras().getString("data_desc");
        nameActivity= getIntent().getExtras().getString("nameActivity");

        TextView txtNama    = findViewById(R.id.txtNamaBarang);
        TextView txtHarga   = findViewById(R.id.txtHargaBarang);
        TextView txtDesc    = findViewById(R.id.txtDesc);
        ImageView img       = findViewById(R.id.imageView);
        Button btnBeli      = findViewById(R.id.btnBeli);
        Button btnTroli     = findViewById(R.id.btnChart);

        if (nameActivity.equals("troli")){
            FrameLayout.LayoutParams layoutParams;
            btnBeli.setVisibility(View.GONE);
            btnTroli.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            RelativeLayout.LayoutParams position = (RelativeLayout.LayoutParams) btnTroli.getLayoutParams();
            position.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            btnTroli.setLayoutParams(position);
            btnTroli.setText("Pembayaran");
            btnTroli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else{
            btnTroli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), TroliActivity.class));
                }
            });
        }

        txtNama.setText(nama);
        txtHarga.setText(harga);
        txtDesc.setText(deskripsi);
        try {
            img.setImageResource(imgList[no-1]);
        } catch (Exception e) {
            img.setImageResource(R.mipmap.ic_launcher);
        }

    }
}
