package com.biblioteca.model;

import java.util.Date;

public class Miembro {
    private int miembroId;
    private String nombre;
    private String apellido;
    private Date fechaInscripcion;


    public Miembro(int miembroId, String nombre, String apellido, Date fechaInscripcion) {
        this.miembroId = miembroId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaInscripcion = fechaInscripcion;
    }


    public Miembro(String nombre, String apellido, Date fechaInscripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaInscripcion = fechaInscripcion;
    }



    public int getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(int miembroId) {
        this.miembroId = miembroId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        return "Miembro [ID=" + miembroId + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Fecha Inscripción=" + fechaInscripcion + "]";
    }
}