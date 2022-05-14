package com.example.evidenciafut.entidades;

public class Partidos {
    private int id;
    private String Rival;
    private String golesFavor;
    private String golesContra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRival() {
        return Rival;
    }

    public void setRival(String rival) {
        Rival = rival;
    }

    public String getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(String golesFavor) {
        this.golesFavor = golesFavor;
    }

    public String getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(String golesContra) {
        this.golesContra = golesContra;
    }
}
