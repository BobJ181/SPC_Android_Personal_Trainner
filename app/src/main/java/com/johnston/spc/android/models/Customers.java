package com.johnston.spc.android.models;

import android.content.Context;

import com.johnston.spc.android.database.ReaderDbHelper;
import com.johnston.spc.android.database.SqlLite;

import java.util.ArrayList;

/**
 * Created by 3johnr181 on 9/6/2017.
 */

public class Customers {

    private ReaderDbHelper db;
    private SqlLite s;

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

    public Customers(Context c)
    {
        db = new ReaderDbHelper(c);
        s = new SqlLite();
    }

    public String GetFulleName()
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

    public ArrayList<Customers> getCustomers()
    {
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
        }


    }
}
