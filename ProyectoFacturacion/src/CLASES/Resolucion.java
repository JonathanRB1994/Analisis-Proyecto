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
public class Resolucion {
    private int id;
    private String tipo;    
    private String noResolucion;
    private String noSerie;
    private int noInicial;
    private int noFinal;
    private int noActual;
    private boolean estado;
    private boolean contribuyenteChico;
    private Date fechaAutorizacion;
    private Date fechaIngreso;

    public Resolucion( int id, String tipo, String noResolucion, String noSerie, int noInicial, int noFinal, int noActual, boolean estado, boolean contribuyenteChico, Date fechaAutorizacion, Date fechaIngreso) {        
        this.id = id;
        this.tipo = tipo;
        this.noResolucion = noResolucion;
        this.noSerie = noSerie;
        this.noInicial = noInicial;
        this.noFinal = noFinal;
        this.noActual = noActual;
        this.estado = estado;
        this.contribuyenteChico = contribuyenteChico;
        this.fechaAutorizacion = fechaAutorizacion;
        this.fechaIngreso = fechaIngreso;
    }

    public Resolucion(String tipo, String noResolucion, String noSerie, int noInicial, int noFinal, boolean contribuyenteChico, Date fechaAutorizacion) {
        this.tipo = tipo;
        this.noResolucion = noResolucion;
        this.noSerie = noSerie;
        this.noInicial = noInicial;                
        this.noFinal = noFinal;
        this.contribuyenteChico = contribuyenteChico;
        this.fechaAutorizacion = fechaAutorizacion;
        
        noActual=noInicial;
        estado=true;
        fechaIngreso=new Date();
        
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isContribuyenteChico() {
        return contribuyenteChico;
    }

    public void setContribuyenteChico(boolean contribuyenteChico) {
        this.contribuyenteChico = contribuyenteChico;
    }        

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoResolucion() {
        return noResolucion;
    }

    public void setNoResolucion(String noResolucion) {
        this.noResolucion = noResolucion;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public int getNoInicial() {
        return noInicial;
    }

    public void setNoInicial(int noInicial) {
        this.noInicial = noInicial;
    }

    public int getNoFinal() {
        return noFinal;
    }

    public void setNoFinal(int noFinal) {
        this.noFinal = noFinal;
    }

    public int getNoActual() {
        return noActual;
    }

    public void setNoActual(int noActual) {
        this.noActual = noActual;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    
    
}
