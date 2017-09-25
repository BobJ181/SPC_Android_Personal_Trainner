package com.johnston.spc.android.array_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.johnston.spc.android.models.Customers;
import com.johnston.spc.android.personal_trainer.R;

import java.util.List;

/**
 * Created by 3johnr181 on 9/6/2017.
 */

public class CustomerArrayAdapter extends ArrayAdapter<Customers> implements OnItemClickListener
{
    private final List<Customers> values;
    private final Context context;
    public CustomerArrayAdapter(Context context, List<Customers> v) {
        super(context, R.layout.customer_row, v);
        this.values = v;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.customer_row, parent, false);
        Customers c = (Customers) values.get(position);

        TextView tvFullName = (TextView) rowView.findViewById(R.id.tv_Name);
        TextView tvKnown = (TextView) rowView.findViewById(R.id.tv_Known);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        tvFullName.setText(c.getFullName());

        tvKnown.setText(c.getKnownAsName());

        return rowView;
    }

    public Customers getItem(int position){
        return values.get(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
