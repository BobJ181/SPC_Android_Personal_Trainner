package com.johnston.spc.android.array_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.johnston.spc.android.models.Customers;
import com.johnston.spc.android.models.Session;
import com.johnston.spc.android.personal_trainer.R;

import java.util.List;

/**
 * Created by Bob on 10/3/2017.
 */

public class SessionArrayAdapter extends ArrayAdapter<Session> implements AdapterView.OnItemClickListener {

    private final List<Session> values;
    private final Context context;

    public SessionArrayAdapter(Context context, List<Session> v) {
        super(context, R.layout.session_row, v);
        this.values = v;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.customer_row, parent, false);
        Session s = (Session) values.get(position);

        TextView tvFullName = (TextView) rowView.findViewById(R.id.txt_name);
        TextView tvCustomer = (TextView) rowView.findViewById(R.id.txt_customer);
        tvFullName.setText(s.getSessionName());


        return rowView;
    }

    public Session getItem(int position){
        return values.get(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
