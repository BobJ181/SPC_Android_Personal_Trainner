package com.johnston.spc.android.database;

import android.provider.BaseColumns;

/**
 * Created by 3johnr181 on 9/25/2017.
 */

public final class SqlLite {

    public static class CustomerEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Customer";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_FIRSTNAME = "FirstName";
        public static final String COLUMN_NAME_LASTNAME = "LastName";
        public static final String COLUMN_NAME_KNOWNAS = "KnowAsName";
        public static final String COLUMN_NAME_EMAIL = "Email";
        public static final String COLUMN_NAME_PHONE = "PhoneNumber";
        public static final String COLUMN_NAME_ADDRESSONE = "AddressOne";
        public static final String COLUMN_NAME_ADDRESSTWO = "AddressTwo";
        public static final String COLUMN_NAME_CITY = "City";
        public static final String COLUMN_NAME_STATE = "State";
        public static final String COLUMN_NAME_ZIP = "Zip";
        public static final String COLUMN_NAME_PHOTOSRC = "PhotoSrc";

        public static final String SQL_Create_Customer_Table =
                "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID + " Integer Primary Key AUTOINCREMENT, " +
                COLUMN_NAME_FIRSTNAME + " Text, " + COLUMN_NAME_LASTNAME + " Text, " +
                COLUMN_NAME_KNOWNAS + " Text, " + COLUMN_NAME_EMAIL + " Text, " +
                COLUMN_NAME_PHONE + " Text, " + COLUMN_NAME_ADDRESSONE + " Text, " +
                COLUMN_NAME_ADDRESSTWO + " Text, " + COLUMN_NAME_CITY + " Text, " +
                COLUMN_NAME_STATE + " Text, " + COLUMN_NAME_ZIP + " Text, " +
                COLUMN_NAME_PHOTOSRC + " Text)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String Select_Base =
                "Select " + COLUMN_NAME_ID + ", " + COLUMN_NAME_FIRSTNAME +
                ", " + COLUMN_NAME_LASTNAME + ", " + COLUMN_NAME_KNOWNAS +
                ", " + COLUMN_NAME_EMAIL + ", " + COLUMN_NAME_PHONE +
                ", " + COLUMN_NAME_ADDRESSTWO + ", " + COLUMN_NAME_ADDRESSTWO +
                ", " + COLUMN_NAME_CITY + ", " + COLUMN_NAME_STATE +
                ", " + COLUMN_NAME_ZIP + ", " + COLUMN_NAME_PHOTOSRC +
                " FROM " + TABLE_NAME;

    }
    public static class SessionEntry implements BaseColumns
    {
        public static final String Table_Name = "Sessions";
        public static final String Column_Name_Session_Name = "SessionName";
        public static final String Column_Name_ID = "ID";
        public static final String Column_Name_Customer_ID = "CustomerID";

        public static final String SQL_Create_Session_Table =
                "CREATE TABLE " + Table_Name + " (" +
                        Column_Name_ID + " Integer Primary Key AUTOINCREMENT, " +
                        Column_Name_Customer_ID + " Integer, " +
                        Column_Name_Session_Name + " Text)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Table_Name;
    }

    public static class CustomerBillingEntry implements BaseColumns
    {
        public static final String Table_Name = "CustomerBilling";
        public static final String Column_Name_Card_Number = "CardNumber";
        public static final String Column_Name_ID = "ID";
        public static final String Column_Name_CustomerID = "CustomerID";
        public static final String Column_Name_CCV = "CCV";
        public static final String Column_Name_Address_One = "AddressOne";
        public static final String Column_Name_Address_Two = "AddressTwo";
        public static final String COLUMN_NAME_CITY = "City";
        public static final String COLUMN_NAME_STATE = "State";
        public static final String COLUMN_NAME_ZIP = "Zip";

        public static final String SQL_Create_Customer_Table =
                "CREATE TABLE " + Table_Name + " (" +
                        Column_Name_ID + " Integer Primary Key AUTOINCREMENT, " +
                        Column_Name_CustomerID + " Integer, " +
                        Column_Name_Card_Number + " Text, " + Column_Name_CCV + " Text, " +
                        Column_Name_Address_One + " Text, " + Column_Name_Address_Two + " Text, " +
                        COLUMN_NAME_CITY + " Text, " +
                        COLUMN_NAME_STATE + " Text, " + COLUMN_NAME_ZIP + " Text)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Table_Name;
    }
}
