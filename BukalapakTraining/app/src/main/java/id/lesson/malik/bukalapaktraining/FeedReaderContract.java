package id.lesson.malik.bukalapaktraining;

import android.provider.BaseColumns;

public final class FeedReaderContract {

    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME               = "tb_barang";
        public static final String COLUMN_NAME_ID           = "idBarang";
        public static final String COLUMN_NAME_BARANG       = "namaBarang";
        public static final String COLUMN_NAME_HARGA        = "hargaBarang";
        public static final String COLUMN_NAME_DESKRIPSI    = "deskripsi";
        public static final String COLUMN_NAME_STATUS       = "status";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_BARANG + " TEXT, " +
                    FeedEntry.COLUMN_NAME_HARGA + " TEXT, " +
                    FeedEntry.COLUMN_NAME_DESKRIPSI + " TEXT, " +
                    FeedEntry.COLUMN_NAME_STATUS + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
