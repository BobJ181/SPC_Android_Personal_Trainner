package com.johnston.spc.android.personal_trainer;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.johnston.spc.android.models.CustomerBilling;
import com.johnston.spc.android.models.Customers;

import java.util.ArrayList;

import layout.UserLoggedInFragment;

public class CustomerBillingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, UserLoggedInFragment.OnFragmentInteractionListener {

    private EditText ccn;
    private EditText ccv;
    private TextView fullName;
    private EditText addressOne;
    private EditText addressTwo;
    private EditText city;
    private Spinner state;

    private Customers customers;
    private CustomerBilling customerBilling;

    private Boolean newRecord = true;
    private String CustomerID;
    private String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_billing);

        CustomerID = getIntent().getStringExtra("CUSTOMER_ID");

        fullName = (TextView) findViewById(R.id.txtName);
        ccn = (EditText) findViewById(R.id.txt_ccn);
        ccv =(EditText) findViewById(R.id.txt_ccv);
        addressOne = (EditText) findViewById(R.id.txtAddressOne);
        addressTwo = (EditText) findViewById(R.id.txtAddressTwo);
        city = (EditText) findViewById(R.id.txtCity);
        state = (Spinner) findViewById(R.id.spnState);

        customers = new Customers(this);
        customerBilling = new CustomerBilling(this);

        fullName.setText(customers.getCustomer(Integer.parseInt(CustomerID), false).getFullName());

        CustomerBilling cb = customerBilling.getCustomerBilling(CustomerID);

        if (cb != null) {
            ccn.setText(cb.getCreditCardNumber());
            ccv.setText(cb.getCCV());
            addressOne.setText(cb.getAddressOne());
            addressTwo.setText(cb.getAddressTwo());
            city.setText(cb.getCity());

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.state, R.layout.support_simple_spinner_dropdown_item);

            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            state.setAdapter(adapter);

            newRecord = false;
        }

        Button btn_Save = (Button) findViewById(R.id.btn_save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newRecord)
                {
                    CustomerBilling cb = customerBilling;

                    cb.PutCustomers(cb.Instantiate(Integer.parseInt(CustomerID), ccn.getText().toString(),
                            ccv.getText().toString(),
                            addressOne.getText().toString(),
                            addressTwo.getText().toString(),
                            city.getText().toString(),
                            state.getSelectedItem().toString()));
                }
                else {
                    CustomerBilling cb = customerBilling;

                    cb.UpdateCustomer(cb.Instantiate(Integer.parseInt(ID),
                            Integer.parseInt(CustomerID), ccn.getText().toString(),
                            ccv.getText().toString(),
                            addressOne.getText().toString(),
                            addressTwo.getText().toString(),
                            city.getText().toString(),
                            state.getSelectedItem().toString()));
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
