package com.challenge.bbva.challengebbva.rest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created clazoe
 */
public class Producto {

    @SerializedName("codProd")
    @Expose
    private String codProd;
    @SerializedName("NombreProd")
    @Expose
    private String nombreProd;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("cantidad")
    @Expose
    private String cantidad;

    public String getCodProd() {
        return codProd;
    }
    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }
    public String getNombreProd() {
        return nombreProd;
    }
    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }


}
