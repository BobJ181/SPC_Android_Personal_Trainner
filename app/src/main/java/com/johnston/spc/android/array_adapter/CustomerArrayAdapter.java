package com.johnston.spc.android.array_adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.johnston.spc.android.models.Customers;
import com.johnston.spc.android.personal_trainer.R;

import java.util.List;

/**
 * Created by 3johnr181 on 9/6/2017.
 */

public class CustomerArrayAdapter extends ArrayAdapter<Customers> {
    private final List<Customers> values;
    public CustomerArrayAdapter(Context context, List<Customers> v) {
        super(context, R.layout.customer_row, v);
        this.values = v;
    }
}
