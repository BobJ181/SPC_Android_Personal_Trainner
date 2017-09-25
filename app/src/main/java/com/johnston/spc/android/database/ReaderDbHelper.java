package com.johnston.spc.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.johnston.spc.android.models.Customers;

import java.util.ArrayList;

/**
 * Created by 3johnr181 on 9/25/2017.
 */

public class ReaderDbHelper extends SQLiteOpenHelper {

    public static final String DbName = "Trainer.db";

    public ReaderDbHelper(Context context)
    {
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlLite.CustomerEntry.SQL_Create_Customer_Table);

        ArrayList<Customers> c = Customers.

        db.execSQL(SqlLite.SessionEntry.SQL_Create_Customer_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ToDO:  Come up with a DB Upgrade Policy
    }
}
