package com.readsystem.JpApp.models;

import java.io.Serializable;

public class PopularProductsModel implements Serializable {
    String descripcion;
    String nombre;
    String calificacion;
    int precio;
    String img_url;

    public PopularProductsModel() {
    }

    public PopularProductsModel(String descripcion, String nombre, String calificacion, int precio, String img_url) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.precio = precio;
        this.img_url = img_url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
