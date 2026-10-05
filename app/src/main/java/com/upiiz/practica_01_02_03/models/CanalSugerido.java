package com.upiiz.practica_01_02_03.models;

public class CanalSugerido {
    private String titulo;
    private String seguidores;
    private int fotoResId;

    public CanalSugerido(String titulo, String seguidores, int fotoResId) {
        this.titulo = titulo;
        this.seguidores = seguidores;
        this.fotoResId = fotoResId;
    }

    public String getTitulo() { return titulo; }
    public String getSeguidores() { return seguidores; }
    public int getFotoResId() { return fotoResId; }
}