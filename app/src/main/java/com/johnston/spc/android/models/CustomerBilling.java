package com.johnston.spc.android.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.widget.Toast;

import com.johnston.spc.android.database.ReaderDbHelper;
import com.johnston.spc.android.database.SqlLite;

import java.util.ArrayList;

/**
 * Created by 3johnr181 on 10/2/2017.
 */

public class CustomerBilling {
    private int ID;
    private int CustomerID;
    private String CreditCardNumber;
    private String CCV;
    private String AddressOne;
    private String AddressTwo;
    private String City;
    private String State;
    private Context ctx;

    public CustomerBilling() { }

    public CustomerBilling(Context context) { ctx = context; }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreditCardNumber() {
        return CreditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        CreditCardNumber = creditCardNumber;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
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

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public static CustomerBilling Instantiate()
    {
        return new CustomerBilling();
    }

    public static CustomerBilling Instantiate(int CustomerID, String CCN, String CCV, String AddressOne, String AddressTwo, String City, String State)
    {
        CustomerBilling cb = new CustomerBilling();

        cb.setCustomerID(CustomerID);
        cb.setCreditCardNumber(CCN);
        cb.setCCV(CCV);
        cb.setAddressOne(AddressOne);
        cb.setAddressTwo(AddressTwo);
        cb.setCity(City);
        cb.setState(State);

        return cb;
    }
    public static CustomerBilling Instantiate(int ID, int CustomerID, String CCN, String CCV, String AddressOne, String AddressTwo, String City, String State)
    {
        CustomerBilling cb = new CustomerBilling();

        cb.setID(ID);
        cb.setCustomerID(CustomerID);
        cb.setCreditCardNumber(CCN);
        cb.setCCV(CCV);
        cb.setAddressOne(AddressOne);
        cb.setAddressTwo(AddressTwo);
        cb.setCity(City);
        cb.setState(State);

        return cb;
    }

    public ArrayList<CustomerBilling> getCustomerBillings(String column, String value)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ArrayList<CustomerBilling> cl = new ArrayList<CustomerBilling>();

        String[] proj = {
                SqlLite.CustomerBillingEntry.Column_Name_ID,
                SqlLite.CustomerBillingEntry.Column_Name_CustomerID,
                SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE,
                SqlLite.CustomerBillingEntry.Column_Name_Address_One,
                SqlLite.CustomerBillingEntry.Column_Name_Address_Two,
                SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY,
                SqlLite.CustomerBillingEntry.Column_Name_Card_Number,
                SqlLite.CustomerBillingEntry.Column_Name_CCV
        };


        String sel = "";
        String[] selArg = { };

        Cursor c = d.query(
                SqlLite.CustomerBillingEntry.Table_Name,
                proj,
                sel,
                selArg,
                null,
                null,
                "");

        while (c.moveToNext())
        {
            int idPos = c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_ID);
            int id = c.getInt(idPos);

            cl.add(Instantiate(id,
                    c.getInt(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_CustomerID)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Card_Number)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_CCV)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Address_One)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Address_Two)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY)),
                    c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE))
            ));
        }

        d.close();
        return cl;
    }

    public CustomerBilling getCustomerBilling(String Value)
    {

        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        String[] proj = {
                SqlLite.CustomerBillingEntry.Column_Name_ID,
                SqlLite.CustomerBillingEntry.Column_Name_CustomerID,
                SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE,
                SqlLite.CustomerBillingEntry.Column_Name_Address_One,
                SqlLite.CustomerBillingEntry.Column_Name_Address_Two,
                SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY,
                SqlLite.CustomerBillingEntry.Column_Name_Card_Number,
                SqlLite.CustomerBillingEntry.Column_Name_CCV
        };

        String sel = SqlLite.CustomerBillingEntry.Column_Name_CustomerID + " = ?";
        String[] selArgs = { Value };


        Cursor c = d.query(SqlLite.CustomerBillingEntry.Table_Name, proj, sel, selArgs, "", "", "");



        if (c.getCount() == 0) { d.close(); return null;  }

        d.close();

        int idPos = c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_ID);
        int id = c.getInt(idPos);

        return CustomerBilling.Instantiate(id,
                c.getInt(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_CustomerID)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Card_Number)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_CCV)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Address_One)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Address_Two)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE)));


    }
    public void PutCustomers(CustomerBilling cb)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        long rowID = 0;

        ContentValues v = new ContentValues();
        v.put(SqlLite.CustomerBillingEntry.Column_Name_Card_Number, cb.getCreditCardNumber());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_CustomerID, cb.getCustomerID());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_CCV, cb.getCCV());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_Address_One, cb.getAddressOne());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_Address_Two, cb.getAddressTwo());
        v.put(SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY, cb.getCity());
        v.put(SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE, cb.getState());

        rowID = d.insert(SqlLite.CustomerBillingEntry.Table_Name, null, v);

        d.close();
    }

    public void DeleteCustomer(CustomerBilling c)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        String selection = SqlLite.CustomerBillingEntry.Column_Name_ID + " = ?";

        String[] selArgs = { Integer.toString(c.getID()) };

        d.delete(SqlLite.CustomerBillingEntry.Table_Name, selection, selArgs );

        d.close();
    }

    public void UpdateCustomer(CustomerBilling cb)
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put(SqlLite.CustomerBillingEntry.Column_Name_Card_Number, cb.getCreditCardNumber());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_CustomerID, cb.getCustomerID());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_CCV, cb.getCCV());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_Address_One, cb.getAddressOne());
        v.put(SqlLite.CustomerBillingEntry.Column_Name_Address_Two, cb.getAddressTwo());
        v.put(SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY, cb.getCity());
        v.put(SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE, cb.getState());

        String sel = SqlLite.CustomerBillingEntry.Column_Name_ID + " = ?";
        String[] selArgs = { Integer.toString(cb.getID()) };

        int count = d.update(SqlLite.CustomerBillingEntry.Table_Name, v, sel, selArgs);

        d.close();

        CharSequence s = "Customer Data Saved!";
        Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
    }

    public boolean CountDB()
    {
        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();

        Customers c = new Customers(ctx);
        ArrayList<Customers> cl = c.getCustomers();

        long count = DatabaseUtils.queryNumEntries(d, SqlLite.CustomerBillingEntry.Table_Name);

        if (count == 0) {
            for (Customers o : cl) {
                PutCustomers(CustomerBilling.Instantiate(o.getID(), "4444333322221111", "000", "", "", "", "" ));
            }
        }

        d.close();

        return count == 0;
    }
}
