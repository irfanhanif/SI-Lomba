package com.example.fandyaditya.silomba.PengaturanTim.RequestCalon;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class RequestCalonObjek {
    private String idUser;
    private String nama;


    public RequestCalonObjek(String idUser, String nama) {
        this.idUser = idUser;
        this.nama = nama;

    }

    public String getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

}
