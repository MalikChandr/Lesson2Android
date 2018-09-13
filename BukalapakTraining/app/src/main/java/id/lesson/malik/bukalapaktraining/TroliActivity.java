package id.lesson.malik.bukalapaktraining;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class TroliActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_troli);

        ArrayList<Barang> itemIds = new ArrayList<Barang>();
        String nameActivity = "troli";
        adapter = new BarangAdapter(this, itemIds, imgList, nameActivity);
        getData();
        ListView listView = findViewById(R.id.listTroli);
        listView.setAdapter(adapter);
//        listView.setDivider(this.getResources().getDrawable(R.drawable.transparent));
        listView.setDividerHeight(5);

    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }

    public void getData(){

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_NAME_ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG,
                FeedReaderContract.FeedEntry.COLUMN_NAME_HARGA,
                FeedReaderContract.FeedEntry.COLUMN_NAME_DESKRIPSI,
                FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS
        };

        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_STATUS + " = ?";
        String[] selectionArgs = { "true" };

//        String sortOrder =
//                FeedReaderContract.FeedEntry.COLUMN_NAME_BARANG + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
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
}

