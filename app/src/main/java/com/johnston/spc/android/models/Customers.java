package com.johnston.spc.android.models;

import java.util.List;

/**
 * Created by 3johnr181 on 9/6/2017.
 */

public class Customers {
    private String FirstName;
    private String LastName;
    private String KnownAsName;
    private String Email;
    private String PhoneNumber;
    private String AddressOne;
    private String AddressTwo;
    private String City;
    private String State;
    private String PhotoSrc;

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

    public List<Customer> CustomerList()
    {
        List<Customer> cl = null;
        return null;
    }
    public static Customer Instantiate()
    {
        return new Customer();
    }
    public static Customer Instantiate(String FirstName, String LastName, String KnownAs, String Email, String PhoneNumber, String AddressOne, String AddressTwo, String City, String State, String PhotoUrl)
    {
        Customer c = new Customer();
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
}
