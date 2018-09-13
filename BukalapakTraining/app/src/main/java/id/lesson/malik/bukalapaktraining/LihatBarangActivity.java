package id.lesson.malik.bukalapaktraining;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LihatBarangActivity extends AppCompatActivity {
    BarangAdapter adapter;
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
    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);
        ArrayList<Barang> itemIds = new ArrayList<Barang>();
        String nameActivity = "barang";
        adapter = new BarangAdapter(this, itemIds, imgList, nameActivity);

        final ListView listView = findViewById(R.id.listBarang);
        listView.setAdapter(adapter);
//        listView.setDivider(this.getResources().getDrawable(R.drawable.transparent));
        listView.setDividerHeight(5);

        getData();
        alertDialogAdd();
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }

    public void getData(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_NAME_ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG,
                FeedReaderContract.FeedEntry.COLUMN_NAME_HARGA,
                FeedReaderContract.FeedEntry.COLUMN_NAME_DESKRIPSI,
                FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS
        };

//        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG + " = ?";
//        String[] selectionArgs = { "Drone mavic pro" };

//        String sortOrder =
//                FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        while(cursor.moveToNext()) {
            int idBarang = cursor.getInt(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ID));
            String nmBarang = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG));
            String hgBarang = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_HARGA));
            String desc = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_DESKRIPSI));
            String stat = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS));
            adapter.add(new Barang(idBarang, nmBarang, hgBarang, desc, stat));
        }
        cursor.close();
        db.close();
    }

    public void alertDialogAdd(){
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                LayoutInflater inflater = LihatBarangActivity.this.getLayoutInflater();
                View mView = inflater.inflate(R.layout.layout_input_barang, null);
                final EditText edtNama    = mView.findViewById(R.id.edtBarang);
                final EditText edtHarga   = mView.findViewById(R.id.edtHarga);
                final EditText edtDesc    = mView.findViewById(R.id.edtDesc);
                final String status       = "false";

                // Add action buttons
                builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtNama.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "Harap isi nama barang", Toast.LENGTH_LONG).show();

                        }else if (edtHarga.getText().toString().equals("Rp. ")){
                            Toast.makeText(getApplicationContext(), "Harap isi harga barang", Toast.LENGTH_LONG).show();
                        }else if (edtDesc.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "Harap isi deskripsi barang", Toast.LENGTH_LONG).show();
                        }else{
                            FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                            SQLiteDatabase db = mDbHelper.getWritableDatabase();

                            ContentValues values = new ContentValues();
                            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG, edtNama.getText().toString());
                            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_HARGA, edtHarga.getText().toString());
                            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DESKRIPSI, edtDesc.getText().toString());
                            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS, status);

                            db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
    }
}
