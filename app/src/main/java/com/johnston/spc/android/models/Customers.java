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
 * Created by 3johnr181 on 9/6/2017.
 */

public class Customers {

    private ReaderDbHelper db;
    private SqlLite s;
    private Context context;

    private int ID;
    private String FirstName;
    private String LastName;
    private String KnownAsName;
    private String Email;
    private String PhoneNumber;
    private String AddressOne;
    private String AddressTwo;
    private String City;
    private String State;
    private String Zip;
    private String PhotoSrc;

    public Customers ()
    {
        s = new SqlLite();
        context = null;
    }
    public Customers(Context c)
    {
        db = new ReaderDbHelper(c);
        s = new SqlLite();
        context = c;
    }

    public String getFullName()
    {
        return FirstName + " (" + KnownAsName + ") " + LastName;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getKnownAsName() {
        return KnownAsName;
    }

    public void setKnownAsName(String knownAsName) {
        KnownAsName = knownAsName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddressOne() {
        return AddressOne;
    }

    public void setAddressOne(String addressOne) {
        AddressOne = addressOne;
    }

    public String getAddressTwo() {
        return AddressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        AddressTwo = addressTwo;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPhotoSrc() {
        return PhotoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        PhotoSrc = photoSrc;
    }

    public String getZip() { return Zip; }

    public void setZip(String zip) { Zip = zip; }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Customers> getCustomers()
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(context);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ArrayList<Customers> cl = new ArrayList<Customers>();

        String[] proj = {
                SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC,
                SqlLite.CustomerEntry.COLUMN_NAME_ZIP,
                SqlLite.CustomerEntry.COLUMN_NAME_STATE,
                SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO,
                SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE,
                SqlLite.CustomerEntry.COLUMN_NAME_CITY,
                SqlLite.CustomerEntry.COLUMN_NAME_EMAIL,
                SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME,
                SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME,
                SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS,
                SqlLite.CustomerEntry.COLUMN_NAME_PHONE
        };

        Cursor c = d.query(
                SqlLite.CustomerEntry.TABLE_NAME,
                proj,
                "",
                null,
                "",
                "",
                "");

        while (c.moveToNext())
        {
               cl.add(Instantiate(c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_PHONE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_CITY)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_STATE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ZIP)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC))
               ));
        }

        d.close();
        return cl;
    }

    public ArrayList<Customers> getCustomers(String column, String value)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(context);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ArrayList<Customers> cl = new ArrayList<Customers>();

        String[] proj = {
                SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC,
                SqlLite.CustomerEntry.COLUMN_NAME_ZIP,
                SqlLite.CustomerEntry.COLUMN_NAME_STATE,
                SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO,
                SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE,
                SqlLite.CustomerEntry.COLUMN_NAME_CITY,
                SqlLite.CustomerEntry.COLUMN_NAME_EMAIL,
                SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME,
                SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME,
                SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS,
                SqlLite.CustomerEntry.COLUMN_NAME_PHONE
        };


        String sel = SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS + " = ?";
        String[] selArg = { "Bob" };

        Cursor c = d.query(
                SqlLite.CustomerEntry.TABLE_NAME,
                proj,
                sel,
                selArg,
                null,
                null,
                "");

        while (c.moveToNext())
        {
            cl.add(Instantiate(c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_PHONE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_CITY)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_STATE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ZIP)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC))
            ));
        }

        d.close();
        return cl;
    }

    public Customers getCustomer(int Value, boolean byPosition)
    {

        ReaderDbHelper dbHelper = new ReaderDbHelper(context);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ArrayList<Customers> cl = new ArrayList<Customers>();

        String[] proj = {
                SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC,
                SqlLite.CustomerEntry.COLUMN_NAME_ZIP,
                SqlLite.CustomerEntry.COLUMN_NAME_STATE,
                SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO,
                SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE,
                SqlLite.CustomerEntry.COLUMN_NAME_CITY,
                SqlLite.CustomerEntry.COLUMN_NAME_EMAIL,
                SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME,
                SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME,
                SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS,
                SqlLite.CustomerEntry.COLUMN_NAME_PHONE
        };

        String sel = "";
        String[] selArgs = { };

        if (!byPosition){
            //ToDo:  For now, we are just going to return the first record.  But this is where you would search by ID.
            //ToDO:  So, nothing here for now...
        }

        Cursor c = d.query(
                SqlLite.CustomerEntry.TABLE_NAME,
                proj,
                sel,
                selArgs,
                "",
                "",
                "");

        while (c.moveToNext())
        {
            cl.add(Instantiate(c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_EMAIL)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_PHONE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_CITY)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_STATE)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_ZIP)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC))
            ));
        }

        d.close();

        //ToDo:  This really should be getting the first record, because we are assuming that we are searching by
        //ToDo:  a value, not just a position...
        return cl.get(Value);
    }

    public static ArrayList<Customers> CustomerList()
    {
        ArrayList<Customers> cl = new ArrayList<Customers>();
        cl.add(Instantiate("Robert", "Johnston", "Bob", "", "", "", "", "", "", "", ""));
        cl.add(Instantiate("John", "Johnston", "John", "", "", "", "", "", "", "", ""));
        cl.add(Instantiate("Jane", "Johnston", "Jane", "", "", "", "", "", "", "", ""));
        cl.add(Instantiate("Joe", "Johnston", "Joe", "", "", "", "", "", "", "", ""));
        cl.add(Instantiate("Joy", "Johnston", "Joy", "", "", "", "", "", "", "", ""));

        return cl;
    }
    public static Customers Instantiate()
    {
        return new Customers();
    }
    public static Customers Instantiate(int ID, String FirstName, String LastName, String KnownAs, String Email, String PhoneNumber, String AddressOne, String AddressTwo, String City, String State,String Zip, String PhotoUrl)
    {
        Customers c = new Customers();
        c.setID(ID);
        c.setAddressOne(AddressOne);
        c.setAddressTwo(AddressTwo);
        c.setCity(City);
        c.setEmail(Email);
        c.setFirstName(FirstName);
        c.setLastName(LastName);
        c.setKnownAsName(KnownAs);
        c.setPhoneNumber(PhoneNumber);
        c.setState(State);
        c.setPhotoSrc(PhotoUrl);

        return c;
    }
    public static Customers Instantiate(String FirstName, String LastName, String KnownAs, String Email, String PhoneNumber, String AddressOne, String AddressTwo, String City, String State, String Zip, String PhotoUrl)
    {
        Customers c = new Customers();
        c.setAddressOne(AddressOne);
        c.setAddressTwo(AddressTwo);
        c.setCity(City);
        c.setEmail(Email);
        c.setFirstName(FirstName);
        c.setLastName(LastName);
        c.setKnownAsName(KnownAs);
        c.setPhoneNumber(PhoneNumber);
        c.setState(State);
        c.setPhotoSrc(PhotoUrl);

        return c;
    }
    public void PutCustomers(ArrayList<Customers> cl)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(context);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        long rowID = 0;

        for (Customers c : cl ) {
            ContentValues v = new ContentValues();
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC, c.getPhotoSrc());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_ZIP, c.getZip());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_STATE, c.getState());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO, c.getAddressTwo());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE, c.getAddressOne());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_CITY, c.getCity());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_EMAIL, c.getEmail());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME, c.getFirstName());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME, c.getLastName());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS, c.getKnownAsName());
            v.put(SqlLite.CustomerEntry.COLUMN_NAME_PHONE, c.getPhoneNumber());
            rowID = d.insert(SqlLite.CustomerEntry.TABLE_NAME, null, v);
        }
        d.close();
    }

    public void DeleteCustomer(Customers c)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(context);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        String selection = SqlLite.CustomerEntry.COLUMN_NAME_ID + " = ?";

        String[] selArgs = { Integer.toString(c.getID()) };

        d.delete(SqlLite.CustomerEntry.TABLE_NAME, selection, selArgs );

        d.close();
    }

    public void UpdateCustomer(Customers c)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(context);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(SqlLite.CustomerEntry.COLUMN_NAME_PHOTOSRC, c.getPhotoSrc());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_ZIP, c.getZip());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_STATE, c.getState());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSTWO, c.getAddressTwo());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_ADDRESSONE, c.getAddressOne());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_CITY, c.getCity());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_EMAIL, c.getEmail());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_FIRSTNAME, c.getFirstName());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_LASTNAME, c.getLastName());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_KNOWNAS, c.getKnownAsName());
        v.put(SqlLite.CustomerEntry.COLUMN_NAME_PHONE, c.getPhoneNumber());

        String sel = SqlLite.CustomerEntry.COLUMN_NAME_ID + " = ?";
        String[] selArgs = { Integer.toString(c.getID()) };

        int count = d.update(SqlLite.CustomerEntry.TABLE_NAME, v, sel, selArgs);

        d.close();

        CharSequence s = "Customer Data Saved!";
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    public void CountDB()
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(context);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        long count = DatabaseUtils.queryNumEntries(d, SqlLite.CustomerEntry.TABLE_NAME);

        if (count == 0)
        {
            PutCustomers(Customers.CustomerList());
        }

        d.close();
    }
}
