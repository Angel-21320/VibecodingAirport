package com.cerme.vibecodingairport;

public class Vuelo {
    private String numeroVuelo;
    private String aerolinea;
    private String destino;
    private String horaSalida;

    public Vuelo(String numeroVuelo, String aerolinea, String destino, String horaSalida) {
        this.numeroVuelo = numeroVuelo;
        this.aerolinea = aerolinea;
        this.destino = destino;
        this.horaSalida = horaSalida;
    }

    public String getNumeroVuelo() { return numeroVuelo; }
    public String getAerolinea() { return aerolinea; }
    public String getDestino() { return destino; }
    public String getHoraSalida() { return horaSalida; }
}

