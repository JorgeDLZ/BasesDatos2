package com.biblioteca.model;

public class Autor {
    private int autorId;
    private String nombre;
    private String apellido;
    private String nacionalidad;


    public Autor(int autorId, String nombre, String apellido, String nacionalidad) {
        this.autorId = autorId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    public Autor(String nombre, String apellido, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Autor [ID=" + autorId + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Nacionalidad=" + nacionalidad + "]";
    }
}