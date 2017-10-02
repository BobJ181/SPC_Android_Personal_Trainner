package com.johnston.spc.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 3johnr181 on 9/25/2017.
 */

public class ReaderDbHelper extends SQLiteOpenHelper {

    private static Context c;

    public static final String DbName = "PTrainer5.db";
    public ReaderDbHelper(Context context)
    {
        super(context, DbName, null, 1);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlLite.CustomerEntry.SQL_Create_Customer_Table);
        db.execSQL(SqlLite.CustomerBillingEntry.SQL_Create_Customer_Table);
        db.execSQL(SqlLite.SessionEntry.SQL_Create_Customer_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ToDO:  Come up with a DB Upgrade Policy, at this point we are just going to Delete and start over...
        db.execSQL(SqlLite.CustomerEntry.SQL_DELETE_ENTRIES);
        db.execSQL(SqlLite.CustomerBillingEntry.SQL_DELETE_ENTRIES);
        db.execSQL(SqlLite.SessionEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
