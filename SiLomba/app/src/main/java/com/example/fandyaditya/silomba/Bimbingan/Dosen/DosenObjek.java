package com.example.fandyaditya.silomba.Bimbingan.Dosen;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class DosenObjek {
    private String id;
    private String nama;
    private String noHp;
    private String jurusan;

    public DosenObjek(String id, String nama, String noHp, String jurusan) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
        this.jurusan = jurusan;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }
    public String getJurusan(){
        return jurusan;
    }
}
