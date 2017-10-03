package com.johnston.spc.android.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.johnston.spc.android.database.ReaderDbHelper;
import com.johnston.spc.android.database.SqlLite;

import java.util.ArrayList;

/**
 * Created by 3johnr181 on 10/3/2017.
 */

public class Session {
    private int ID;
    private String SessionName;

    private Context ctx;

    public Session() {}

    public Session(Context c)
    {
        ctx = c;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSessionName() {
        return SessionName;
    }

    public void setSessionName(String sessionName) {
        SessionName = sessionName;
    }

    public static Session Instantiate() { return new Session(); }

    public static Session Instantiate(String sessionName) {
        Session s = new Session();
        s.setSessionName(sessionName);

        return s;
    }

    public static Session Instantiate(int ID, String sessionName) {
        Session s = new Session();
        s.setSessionName(sessionName);
        s.setID(ID);

        return s;
    }

    public static ArrayList<Session> SessionsList()
    {
        ArrayList<Session> sl = new ArrayList<Session>();

        sl.add(Session.Instantiate("Monday Session"));
        sl.add(Session.Instantiate("Thursday Session"));
        sl.add(Session.Instantiate("Saturday Session"));

        return sl;
    }

    public ArrayList<Session> GetSessions()
    {
        ArrayList<Session> sl = new ArrayList<Session>();

        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        String[] proj = {
                SqlLite.SessionEntry.Column_Name_ID,
                SqlLite.SessionEntry.Column_Name_Session_Name
        };


        String sel = "";
        String[] selArg = { "" };

        Cursor c = d.query(
                SqlLite.SessionEntry.Table_Name,
                proj,
                sel,
                selArg,
                null,
                null,
                "");

        while (c.moveToNext())
        {
            sl.add(Instantiate(c.getInt(c.getColumnIndexOrThrow(SqlLite.SessionEntry.Column_Name_ID)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.SessionEntry.Column_Name_Session_Name))
            ));
        }

        d.close();
        return sl;
    }

    public Session GetSession(String Value)
    {
        ArrayList<Session> sl = new ArrayList<Session>();

        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        String[] proj = {
                SqlLite.SessionEntry.Column_Name_ID,
                SqlLite.SessionEntry.Column_Name_Session_Name
        };


        String sel = SqlLite.SessionEntry.Column_Name_ID + " = ?";
        String[] selArg = { Value };

        Cursor c = d.query(
                SqlLite.SessionEntry.Table_Name,
                proj,
                sel,
                selArg,
                null,
                null,
                "");

        while (c.moveToNext())
        {
            sl.add(Instantiate(c.getInt(c.getColumnIndexOrThrow(SqlLite.SessionEntry.Column_Name_ID)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.SessionEntry.Column_Name_Session_Name))
            ));
        }

        d.close();
        return sl.get(0) != null ? sl.get(0) : null;
    }

    public void UpdateSession(Session s)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(SqlLite.SessionEntry.Column_Name_Session_Name, s.getSessionName());

        String sel = SqlLite.SessionEntry.Column_Name_Session_Name + " = ?";
        String[] selArgs = { Integer.toString(s.getID()) };

        int count = d.update(SqlLite.SessionEntry.Table_Name, v, sel, selArgs);

        d.close();

        CharSequence cs = "Customer Data Saved!";
        Toast.makeText(ctx, cs, Toast.LENGTH_LONG).show();
    }

    public void PutSession(Session s)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        long rowID = 0;

        ContentValues v = new ContentValues();
        v.put(SqlLite.SessionEntry.Column_Name_Session_Name, s.getSessionName());
        rowID = d.insert(SqlLite.SessionEntry.Table_Name, null, v);

        d.close();
    }

    public void Populated()
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        long count = DatabaseUtils.queryNumEntries(d, SqlLite.CustomerEntry.TABLE_NAME);

        if (count == 0) { PutSession(Session.SessionsList().get(0)); }
        if (count == 0) { PutSession(Session.SessionsList().get(0)); }

        d.close();
    }

}
