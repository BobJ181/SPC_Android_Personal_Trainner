package com.johnston.spc.android.personal_trainer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.johnston.spc.android.models.Customers;

import layout.UserLoggedInFragment;

public class CustomerEditActivity extends AppCompatActivity implements UserLoggedInFragment.OnFragmentInteractionListener {

    private EditText firstName;
    private EditText lastName;
    private EditText knownAs;
    private EditText email;
    private EditText phone;
    private EditText addressOne;
    private EditText addressTwo;
    private EditText city;
    private Spinner state;
    private EditText zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Customers c = new Customers(this);

        firstName = (EditText) findViewById(R.id.txtFirstName);
        lastName =(EditText) findViewById(R.id.txtLastName);
        knownAs = (EditText) findViewById(R.id.txtKnownAsName);
        email = (EditText) findViewById(R.id.txtEmail);
        phone = (EditText) findViewById(R.id.txtPhone);
        addressOne = (EditText) findViewById(R.id.txtAddressOne);
        addressTwo = (EditText) findViewById(R.id.txtAddressTwo);
        city = (EditText) findViewById(R.id.txtCity);
        zip = (EditText) findViewById(R.id.txt_zip);
        state = (Spinner) findViewById(R.id.spnState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.state, R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        state.setAdapter(adapter);

        Button btn_Save = (Button) findViewById(R.id.btn_save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btn_pay = (Button) findViewById(R.id.btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerEditActivity.this, CustomerBillingActivity.class));
            }
        });

        Button btn_photo = (Button) findViewById(R.id.btn_photo);

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerEditActivity.this, CustomerPictureActivity.class));
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
