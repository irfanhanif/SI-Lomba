package com.example.fandyaditya.silomba.PengaturanTim.RequestCalon;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class RequestCalonObjek {
    String id;
    String nama;
    String job;

    public RequestCalonObjek(String id, String nama, String job) {
        this.id = id;
        this.nama = nama;
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJob() {
        return job;
    }
}
