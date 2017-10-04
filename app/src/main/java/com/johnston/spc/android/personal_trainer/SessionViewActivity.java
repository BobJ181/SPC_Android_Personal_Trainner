package com.johnston.spc.android.personal_trainer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.johnston.spc.android.array_adapter.CustomerArrayAdapter;
import com.johnston.spc.android.models.Customers;
import com.johnston.spc.android.models.Session;

import java.util.ArrayList;

import layout.UserLoggedInFragment;

public class SessionViewActivity extends AppCompatActivity implements UserLoggedInFragment.OnFragmentInteractionListener {

    private EditText txtName;
    private Spinner spnCustomer;
    private Button btnSave;
    private Button btnComplete;

    private Session session;
    private Customers customer;

    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_view);

        ID = 0;

        Session s = new Session(this);
        Customers c = new Customers(this);

        session = s;
        customer = c;

        txtName = (EditText) findViewById(R.id.txt_name);
        spnCustomer = (Spinner) findViewById(R.id.spnCustomer);

        btnComplete = (Button) findViewById(R.id.btn_Complete);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Customers> cl = customer.getCustomers();
                Customers c = new Customers();
                for (Customers o: cl) {
                    if (o.getFullName().equalsIgnoreCase(spnCustomer.getSelectedItem().toString())) c = o;
                }

                CharSequence t = "Clicked... @ Position " + Integer.toString(c.getID());
                Session s = session.GetSession(Integer.toString(ID));

                Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SessionViewActivity.this, SessionBillingActivity.class);
                intent.putExtra("ID", Integer.toString(s.getID()));

                startActivity(intent);
            }
        });

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Customers> cl = customer.getCustomers();
                Customers c = new Customers();
                for (Customers o: cl) {
                    if (o.getFullName().equalsIgnoreCase(spnCustomer.getSelectedItem().toString())) c = o;
                }

                session.PutSession(Session.Instantiate(c.getID(), txtName.getText().toString()));
                Intent intent = new Intent(SessionViewActivity.this, SessionListActivity.class);
                startActivity(intent);
            }
        });

        String position = getIntent().getStringExtra("ID");

        Session sess = s.GetSession(position);

        if (sess != null) {
            Customers cust = c.getCustomer(sess.getCustID(), true);
            ID = sess.getCustID();

            txtName.setText(sess.getSessionName());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, c.getCustomerNames());

            spnCustomer.setAdapter(adapter);
            int index = 0;

            for (int i = 0; i < spnCustomer.getCount(); i++) {
                if (spnCustomer.getItemAtPosition(i).toString().equalsIgnoreCase(cust.getFirstName() + " " + cust.getLastName())) {
                    index = i;
                }
            }

            spnCustomer.setSelection(index);
        }
        else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, c.getCustomerNames());

            spnCustomer.setAdapter(adapter);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
