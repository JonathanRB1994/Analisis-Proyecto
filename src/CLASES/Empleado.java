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
public class Empleado {
    private int id;
    private String nombre;
    private String apellidos;
    private boolean estado;
    private String clave;
    private String email;
    private String telefono;
    private int rol;

    public Empleado(int id, String nombre, String apellidos, boolean estado, String clave, String email, String telefono, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estado = estado;
        this.clave = clave;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    public Empleado(String nombre, String apellidos, String clave, String email, String telefono, int rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getRol() {
        return rol;
    }

    public void setIdRol(int rol) {
        this.rol = rol;
    }
    
    
}
