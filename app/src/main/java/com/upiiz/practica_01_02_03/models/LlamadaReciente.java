package com.upiiz.practica_01_02_03.models;

public class LlamadaReciente {
    private String nombreOrTelefono;
    private String fecha;
    private int fotoResId;

    public LlamadaReciente(String nombreOrTelefono, String fecha, int fotoResId) {
        this.nombreOrTelefono = nombreOrTelefono;
        this.fecha = fecha;
        this.fotoResId = fotoResId;
    }

    public String getNombreOrTelefono() { return nombreOrTelefono; }
    public String getFecha() { return fecha; }
    public int getFotoResId() { return fotoResId; }
}