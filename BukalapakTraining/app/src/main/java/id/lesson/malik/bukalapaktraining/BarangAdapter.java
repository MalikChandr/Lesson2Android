package id.lesson.malik.bukalapaktraining;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BarangAdapter extends ArrayAdapter<Barang> {
    // Inisiasi variable untuk array gambar dan nama activity
    private final Integer[] imageList;
    String nameActivity;


    public BarangAdapter(Context context, ArrayList<Barang> barangs, Integer[] imageList, String nameActivity) {
        super(context, 0, barangs);
        this.imageList      = imageList;
        this.nameActivity   = nameActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Barang brg = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(
                            R.layout.item_list,
                            parent,
                            false);
            Button btnChoose = convertView.findViewById(R.id.btnChoose);
            if (brg.status.equals("true")){
                btnChoose.setText("Batal");
                btnChoose.setBackgroundColor(Color.parseColor("#FFDE1818"));

            }else{
                btnChoose.setText("Pilih");
                btnChoose.setBackgroundColor(Color.parseColor("#37dc37"));

            }
        }else{
            Button btnChoose = convertView.findViewById(R.id.btnChoose);
            if (brg.status.equals("true")){
                btnChoose.setText("Batal");
                btnChoose.setBackgroundColor(Color.parseColor("#FFDE1818"));

            }else{
                btnChoose.setText("Pilih");
                btnChoose.setBackgroundColor(Color.parseColor("#37dc37"));

            }
        }

        final Button btnChoose = convertView.findViewById(R.id.btnChoose);
        btnChoose.setTag(position);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Barang brg = getItem(position);

                if (brg.status.equals("false")){
                    brg.status = "true";
                    btnChoose.setText("Batal");
                    btnChoose.setBackgroundColor(Color.parseColor("#FFDE1818"));

                    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(view.getContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    String status = "true";
                    ContentValues values = new ContentValues();
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS, status);

                    String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG + " LIKE ?";
                    String[] selectionArgs = { brg.namaBarang };

                    db.update(
                            FeedReaderContract.FeedEntry.TABLE_NAME,
                            values,
                            selection,
                            selectionArgs);
                    db.close();
                    mDbHelper.close();
                }else{
                    if (nameActivity.equals("troli")){
                        remove(brg);
                        notifyDataSetChanged();
                    }
                    brg.status = "false";
                    btnChoose.setText("Pilih");
                    btnChoose.setBackgroundColor(Color.parseColor("#37dc37"));

                    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(view.getContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    String status = "false";
                    ContentValues values = new ContentValues();
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS, status);

                    String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG + " LIKE ?";
                    String[] selectionArgs = { brg.namaBarang };

                    db.update(
                            FeedReaderContract.FeedEntry.TABLE_NAME,
                            values,
                            selection,
                            selectionArgs);
                    db.close();
                    mDbHelper.close();

                }
            }
        });

        Button btnDetail = convertView.findViewById(R.id.btnDetail);
        btnDetail.setTag(position);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = (Integer) view.getTag();
                Barang barang = getItem(position);

                Intent intent = new Intent(view.getContext(), ViewDetailActivity.class);
                intent.putExtra("data_no", barang.idBarang);
                intent.putExtra("data_nama", barang.namaBarang);
                intent.putExtra("data_harga", barang.hargaBarang);
                intent.putExtra("data_desc", barang.deskripsi);
                intent.putExtra("nameActivity", nameActivity);

                view.getContext().startActivity(intent);
            }
        });


        TextView nmBarang = convertView.findViewById(R.id.txtBarang);
        nmBarang.setText(brg.namaBarang);
        ImageView img = convertView.findViewById(R.id.imgList);

        try {
            img.setImageResource(imageList[brg.idBarang-1]);
        } catch (Exception e) {
            img.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }
}
