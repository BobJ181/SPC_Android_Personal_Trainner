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
import android.widget.TextView;
import android.widget.Toast;

import com.johnston.spc.android.models.Customers;
import com.johnston.spc.android.models.Session;

import layout.UserLoggedInFragment;

public class SessionBillingActivity extends AppCompatActivity implements UserLoggedInFragment.OnFragmentInteractionListener {

    private Spinner state;
    private TextView txtName;
    private Button btnPay;
    private Session session;
    private Customers customer;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_billing);

        state = (Spinner) findViewById(R.id.spnPayment);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.payment, R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        state.setAdapter(adapter);

        Session s = new Session(this);
        Customers c = new Customers(this);

        session = s;
        customer = c;

        txtName = (TextView) findViewById(R.id.txt_FullName);

        String position = getIntent().getStringExtra("ID");

        s = session.GetSession(position);
        c = customer.getCustomer(s.getCustID(), false);
        ID = s.getID();

        txtName.setText(c.getFullName());

        btnPay = (Button) findViewById(R.id.btn_save);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SessionBillingActivity.this, "An email will be sent to you mailbox shortly...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(SessionBillingActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
