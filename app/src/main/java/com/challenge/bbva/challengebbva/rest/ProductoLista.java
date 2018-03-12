package com.challenge.bbva.challengebbva.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by clazoe
 */

public class ProductoLista {

    @SerializedName("listaprod")
    @Expose
    private ArrayList<Producto> productos = null;

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

}


