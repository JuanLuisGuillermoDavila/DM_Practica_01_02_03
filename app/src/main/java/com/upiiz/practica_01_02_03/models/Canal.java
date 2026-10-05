package com.upiiz.practica_01_02_03.models;

public class Canal {
    private String titulo;
    private String ultimoMensaje;
    private String fecha;
    private int fotoResId;
    private int mensajesSinLeer;

    public Canal(String titulo, String ultimoMensaje, String fecha, int fotoResId, int mensajesSinLeer) {
        this.titulo = titulo;
        this.ultimoMensaje = ultimoMensaje;
        this.fecha = fecha;
        this.fotoResId = fotoResId;
        this.mensajesSinLeer = mensajesSinLeer;
    }

    public String getTitulo() { return titulo; }
    public String getUltimoMensaje() { return ultimoMensaje; }
    public String getFecha() { return fecha; }
    public int getFotoResId() { return fotoResId; }
    public int getMensajesSinLeer() { return mensajesSinLeer; }
}