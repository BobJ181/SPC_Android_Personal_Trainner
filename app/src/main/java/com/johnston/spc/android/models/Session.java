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
    private int CustID;

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

    public static Session Instantiate(int CustomerID, String sessionName) {
        Session s = new Session();
        s.setCustID(CustomerID);
        s.setSessionName(sessionName);

        return s;
    }

    public static Session Instantiate(int ID, int CustomerID, String sessionName) {
        Session s = new Session();
        s.setCustID(CustomerID);
        s.setSessionName(sessionName);
        s.setID(ID);

        return s;
    }

    public static ArrayList<Session> SessionsList()
    {
        ArrayList<Session> sl = new ArrayList<Session>();

        sl.add(Session.Instantiate(0, 1, "Monday Session"));
        sl.add(Session.Instantiate(0, 1, "Thursday Session"));
        sl.add(Session.Instantiate(0, 2, "Saturday Session"));

        return sl;
    }

    public ArrayList<Session> GetSessions()
    {
        ArrayList<Session> sl = new ArrayList<Session>();

        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        String[] proj = {
                SqlLite.SessionEntry.Column_Name_ID,
                SqlLite.SessionEntry.Column_Name_Session_Name,
                SqlLite.SessionEntry.Column_Name_Customer_ID
        };


        String sel = "";
        String[] selArg = {  };

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
                    c.getInt(c.getColumnIndexOrThrow(SqlLite.SessionEntry.Column_Name_Customer_ID)),
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
                SqlLite.SessionEntry.Column_Name_Session_Name,
                SqlLite.SessionEntry.Column_Name_Customer_ID
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
                    c.getInt(c.getColumnIndexOrThrow(SqlLite.SessionEntry.Column_Name_Customer_ID)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.SessionEntry.Column_Name_Session_Name))
            ));
        }

        d.close();

        if (sl.size() == 0) return null;

        return sl.get(0);
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
        v.put(SqlLite.SessionEntry.Column_Name_Customer_ID, s.getCustID());
        rowID = d.insert(SqlLite.SessionEntry.Table_Name, null, v);

        d.close();
    }

    public void Populated()
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ArrayList<Session> sl = new ArrayList<Session>();

        long count = DatabaseUtils.queryNumEntries(d, SqlLite.SessionEntry.Table_Name);

        if (count == 0) {
            for (Session s : Session.SessionsList()) {
                PutSession(s);
            }

        }

        d.close();
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int custID) {
        CustID = custID;
    }
}
