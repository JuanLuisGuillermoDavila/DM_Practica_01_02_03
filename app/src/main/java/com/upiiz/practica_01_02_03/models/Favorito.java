package com.upiiz.practica_01_02_03.models;

public class Favorito {
    private String nombre;
    private int fotoResId;

    public Favorito(String nombre, int fotoResId) {
        this.nombre = nombre;
        this.fotoResId = fotoResId;
    }

    public String getNombre() { return nombre; }
    public int getFotoResId() { return fotoResId; }
}