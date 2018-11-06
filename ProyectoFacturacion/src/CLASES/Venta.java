/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.util.Date;

/**
 *
 * @author Jonathan
 */
public class Venta {
    private int id;
    private int transaccion;
    private int cliente;
    private String nombreComercial;
    private String razonSocial;
    private String direccion;
    private String nit;
    private int empleado;
    private String resolucionSistema;
    private Date fechaResolucion;
    private int noMaquina;

    public Venta(int id, int transaccion, int cliente, String nombreComercial, String razonSocial, String direccion, String nit, int empleado, String resolucionSistema, Date fechaResolucion, int noMaquina) {
        this.id = id;
        this.transaccion = transaccion;
        this.cliente = cliente;
        this.nombreComercial = nombreComercial;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.nit = nit;
        this.empleado = empleado;
        this.resolucionSistema = resolucionSistema;
        this.fechaResolucion = fechaResolucion;
        this.noMaquina = noMaquina;
    }

    public int getNoMaquina() {
        return noMaquina;
    }

    public void setNoMaquina(int noMaquina) {
        this.noMaquina = noMaquina;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(int transaccion) {
        this.transaccion = transaccion;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public String getResolucionSistema() {
        return resolucionSistema;
    }

    public void setResolucionSistema(String resolucionSistema) {
        this.resolucionSistema = resolucionSistema;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
    
    
}
