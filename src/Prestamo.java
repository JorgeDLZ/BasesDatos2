package com.biblioteca.model;

import java.util.Date;

public class Prestamo {
    private int prestamoId;
    private int libroId;
    private int miembroId;
    private Date fechaPrestamo;
    private Date fechaDevolucion;


    public Prestamo(int prestamoId, int libroId, int miembroId, Date fechaPrestamo, Date fechaDevolucion) {
        this.prestamoId = prestamoId;
        this.libroId = libroId;
        this.miembroId = miembroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }


    public Prestamo(int libroId, int miembroId, Date fechaPrestamo, Date fechaDevolucion) {
        this.libroId = libroId;
        this.miembroId = miembroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }


    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(int miembroId) {
        this.miembroId = miembroId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Préstamo [ID=" + prestamoId + ", Libro ID=" + libroId + ", Miembro ID=" + miembroId + ", Fecha Préstamo=" + fechaPrestamo + ", Fecha Devolución=" + fechaDevolucion + "]";
    }
}