package com.example.fandyaditya.silomba.PengaturanTim.RequestCalon;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class RequestCalonObjek {
    private String idRequest;
    private String idUser;
    private String nama;
    private String job;

    public RequestCalonObjek(String idRequest, String idUser, String nama, String job) {
        this.idRequest = idRequest;
        this.idUser = idUser;
        this.nama = nama;
        this.job = job;
    }

    public String getIdRequest() {
        return idRequest;
    }

    public String getIdUser() {
        return idUser;
    }


    public String getNama() {
        return nama;
    }

    public String getJob() {
        return job;
    }
}
