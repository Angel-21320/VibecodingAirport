package com.cerme.vibecodingairport;

public class Pasajero {
    private String nombre;
    private String pasaporte;
    private String nacionalidad;
    private boolean huellaRegistrada;

    public Pasajero(String nombre, String pasaporte, String nacionalidad, boolean huellaRegistrada) {
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.nacionalidad = nacionalidad;
        this.huellaRegistrada = huellaRegistrada;
    }

    public String getNombre() { return nombre; }
    public String getPasaporte() { return pasaporte; }
    public String getNacionalidad() { return nacionalidad; }
    public boolean isHuellaRegistrada() { return huellaRegistrada; }
}

