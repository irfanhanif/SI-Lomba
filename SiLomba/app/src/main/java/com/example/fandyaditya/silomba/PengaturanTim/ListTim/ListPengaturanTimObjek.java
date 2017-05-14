package com.example.fandyaditya.silomba.PengaturanTim.ListTim;

/**
 * Created by Fandy Aditya on 5/11/2017.
 */

public class ListPengaturanTimObjek {
    private String idTim;
    private String namaTim;
    private String maxAnggota;
    private String deskripsiTim;
    private String fileFotoprofilTim;
    private String tipeTim;

    public ListPengaturanTimObjek(String idTim, String namaTim, String maxAnggota, String deskripsiTim, String fileFotoprofilTim, String tipeTim) {
        this.idTim = idTim;
        this.namaTim = namaTim;
        this.maxAnggota = maxAnggota;
        this.deskripsiTim = deskripsiTim;
        this.fileFotoprofilTim = fileFotoprofilTim;
        this.tipeTim = tipeTim;
    }

    public String getMaxAnggota() {
        return maxAnggota;
    }

    public String getDeskripsiTim() {
        return deskripsiTim;
    }

    public String getFileFotoprofilTim() {
        return fileFotoprofilTim;
    }

    public String getIdTim() {
        return idTim;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public String getTipeTim() {
        return tipeTim;
    }
}
