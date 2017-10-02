package com.johnston.spc.android.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;

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

    public CustomerBilling getCustomer(String Value)
    {

        ReaderDbHelper dbHelper = new ReaderDbHelper(ctx);
        SQLiteDatabase d = dbHelper.getWritableDatabase();
        ArrayList<Customers> cl = new ArrayList<Customers>();

        String[] proj = {
                SqlLite.CustomerBillingEntry.Column_Name_ID,
                SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE,
                SqlLite.CustomerBillingEntry.Column_Name_Address_One,
                SqlLite.CustomerBillingEntry.Column_Name_Address_Two,
                SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY,
                SqlLite.CustomerBillingEntry.Column_Name_Card_Number,
                SqlLite.CustomerBillingEntry.Column_Name_CCV
        };

        String sel = SqlLite.CustomerBillingEntry.Column_Name_ID;
        String[] selArgs = { Value };

        Cursor c = d.query(SqlLite.CustomerEntry.TABLE_NAME, proj, sel, selArgs, "", "", "");

        d.close();

        return CustomerBilling.Instantiate(c.getInt(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_ID)),
                c.getInt(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_CustomerID)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Card_Number)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_CCV)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Address_One)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.Column_Name_Address_Two)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.COLUMN_NAME_CITY)),
                c.getString(c.getColumnIndexOrThrow(SqlLite.CustomerBillingEntry.COLUMN_NAME_STATE)));
    }
}
