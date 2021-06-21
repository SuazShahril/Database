package com.example.database;

public class PeniModel {

    String kod, nama, status;

    PeniModel(){

    }

    public PeniModel(String nama, String status){
        this.kod = kod;
        this.nama = nama;
        this.status = status;
    }

    public String getKod() {return kod;}

    public void setKod(String kod) {this.kod = kod;}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
