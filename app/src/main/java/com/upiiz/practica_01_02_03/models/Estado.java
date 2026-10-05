package com.upiiz.practica_01_02_03.models;

public class Estado {
    private String nombre;
    private int fotoPerfilResId;
    private int fotoEstadoResId;
    private boolean visto;

    public Estado(String nombre, int fotoPerfilResId, int fotoEstadoResId, boolean visto) {
        this.nombre = nombre;
        this.fotoPerfilResId = fotoPerfilResId;
        this.fotoEstadoResId = fotoEstadoResId;
        this.visto = visto;
    }

    public String getNombre() { return nombre; }
    public int getFotoPerfilResId() { return fotoPerfilResId; }
    public int getFotoEstadoResId() { return fotoEstadoResId; }
    public boolean isVisto() { return visto; }
}