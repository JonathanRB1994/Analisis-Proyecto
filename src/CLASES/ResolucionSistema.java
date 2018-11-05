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
public class ResolucionSistema {
    private int id;
    private String noResolucion;
    private Date fecha;
    private int noMaquiena;

    public ResolucionSistema(int id, String noResolucion, Date fecha, int noMaquina) {
        this.id = id;
        this.noResolucion = noResolucion;
        this.fecha = fecha;
        this.noMaquiena=noMaquina;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNoMaquiena() {
        return noMaquiena;
    }

    public void setNoMaquiena(int noMaquiena) {
        this.noMaquiena = noMaquiena;
    }
    
    
    
}
