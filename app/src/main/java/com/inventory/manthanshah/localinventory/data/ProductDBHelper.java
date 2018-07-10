package com.inventory.manthanshah.localinventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.inventory.manthanshah.localinventory.data.ProductContract.ProductEntry;

public class ProductDBHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = ProductDBHelper.class.getSimpleName();

    /*** Database version. If you change the database schema, you must increment the database version.*/
    public static int DATABASE_VERSION = 1;

    public static String DATABASE_NAME  = "product.db";

    public ProductDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the pets table

        String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + ProductEntry.TABLE_NAME + " ("
                + ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL,"
                + ProductEntry.COLUMN_PRODUCT_PRIZE + " INTEGER NOT NULL, " + ProductEntry.COLUMN_PRODUCT_QUANTITY
                + " INTEGER NOT NULL, " + ProductEntry.COLUMN_SUPPLIER_Name + " TEXT NOT NULL," + ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT)";

        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
