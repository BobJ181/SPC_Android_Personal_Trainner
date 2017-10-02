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

import com.johnston.spc.android.database.SqlLite;
import com.johnston.spc.android.models.Customers;

import java.util.ArrayList;

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

    private boolean newRecord = false;

    private Customers customer;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String position = "";

        customer = new Customers(this);

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

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String s = null;
        if (b != null) {
            s = "Bob";
            newRecord = false;
        }
        if (s != null)
        {
            position = getIntent().getStringExtra("CUSTOMER_ID");

            Customers cust = customer.getCustomers(SqlLite.CustomerEntry.COLUMN_NAME_ID, position).get(0);
            ID = cust.getID();

            firstName.setText(cust.getFirstName());
            lastName.setText(cust.getLastName());
            knownAs.setText(cust.getKnownAsName());
            email.setText(cust.getEmail());
            phone.setText(cust.getPhoneNumber());
            addressOne.setText(cust.getAddressOne());
            addressTwo.setText(cust.getAddressTwo());
            city.setText(cust.getCity());
            zip.setText(cust.getZip());

            if (cust.getState() != null)
            {
                int p = adapter.getPosition(cust.getState());
                state.setSelection(p);
            }
        }

        Button btn_Save = (Button) findViewById(R.id.btn_save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newRecord)
                {
                    ArrayList<Customers> cl = new ArrayList<Customers>();
                    Customers c = customer;

                    cl.add(c.Instantiate(ID, firstName.getText().toString(),
                            lastName.getText().toString(),
                            knownAs.getText().toString(),
                            email.getText().toString(),
                            phone.getText().toString(),
                            addressOne.getText().toString(),
                            addressTwo.getText().toString(),
                            city.getText().toString(),
                            state.getSelectedItem().toString(),
                            zip.getText().toString(),
                            ""));

                    c.PutCustomers(cl);
                }
                else {
                    Customers c = customer;

                    c.UpdateCustomer(c.Instantiate(ID, firstName.getText().toString(),
                            lastName.getText().toString(),
                            knownAs.getText().toString(),
                            email.getText().toString(),
                            phone.getText().toString(),
                            addressOne.getText().toString(),
                            addressTwo.getText().toString(),
                            city.getText().toString(),
                            state.getSelectedItem().toString(),
                            zip.getText().toString(),
                            ""));
                }

            }
        });

        Button btn_pay = (Button) findViewById(R.id.btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerEditActivity.this, CustomerBillingActivity.class);
                intent.putExtra("CUSTOMER_ID", Integer.toString(ID));

                startActivity(intent);
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
