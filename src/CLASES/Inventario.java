/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

/**
 *
 * @author Jonathan
 */
public class Inventario {
    private int id;
    private String producto;
    private String marca;
    private String presentacion;
    private String unidad;
    private double precio;
    private int cantidad;

    public Inventario(String producto, String marca, String presentacion, String unidad, double precio) {
        this.producto = producto;
        this.marca = marca;
        this.presentacion = presentacion;
        this.unidad = unidad;
        this.precio = precio;
    }

    public Inventario(int id, String producto, String marca, String presentacion, String unidad, double precio, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.marca = marca;
        this.presentacion = presentacion;
        this.unidad = unidad;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    
    
}
