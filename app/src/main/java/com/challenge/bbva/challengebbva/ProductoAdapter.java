package com.challenge.bbva.challengebbva;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.challenge.bbva.challengebbva.rest.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clazo
 */

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private Context mContext;
    private List<Producto> productList = new ArrayList<>();


    public ProductoAdapter(@NonNull Context context,  ArrayList<Producto> list) {
        super(context, 0 , list);
        mContext = context;
        productList = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_listview,parent,false);

        Producto productoSeleccionado = productList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView);
        name.setText(productoSeleccionado.getNombreProd() );

        return listItem;
    }
}
