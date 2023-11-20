package com.readsystem.JpApp.models;

public class MyCartModel {

    String tiempoActual;
    String fechaActual;
    String productoNombre;
    String productoPrecio;
    String totalCantidad;
    int totalPrecio;

    public MyCartModel() {
    }

    public MyCartModel(String tiempoActual, String fechaActual, String productoNombre, String productoPrecio, String totalCantidad, int totalPrecio) {
        this.tiempoActual = tiempoActual;
        this.fechaActual = fechaActual;
        this.productoNombre = productoNombre;
        this.productoPrecio = productoPrecio;
        this.totalCantidad = totalCantidad;
        this.totalPrecio = totalPrecio;
    }

    public String getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(String tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(String productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public String getTotalCantidad() {
        return totalCantidad;
    }

    public void setTotalCantidad(String totalCantidad) {
        this.totalCantidad = totalCantidad;
    }

    public int getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(int totalPrecio) {
        this.totalPrecio = totalPrecio;
    }
}
