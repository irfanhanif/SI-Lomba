package com.example.fandyaditya.silomba;

import com.example.fandyaditya.silomba.Bimbingan.BimbinganObjek;
import com.example.fandyaditya.silomba.Bimbingan.Dosen.DosenObjek;
import com.example.fandyaditya.silomba.ListLomba.ListLombaObjek;
import com.example.fandyaditya.silomba.ListLomba.ListTimObjek;
import com.example.fandyaditya.silomba.PengaturanTim.ListTim.ListPengaturanTimObjek;
import com.example.fandyaditya.silomba.PengaturanTim.RequestCalon.RequestCalonObjek;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fandy Aditya on 5/1/2017.
 */

public class ParseJSON {
    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    public String statusCodeParse(){
        JSONObject jsonObject;
        String parseVal = null;

        try {
            jsonObject = new JSONObject(json);
            parseVal = jsonObject.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<String> detailBimbinganParse(){
        JSONObject jsonObject;
        List<String> parseVal = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            parseVal.add(jsonObject.getString("tanggal_bimbingan"));
            parseVal.add(jsonObject.getString("comment_bimbingan"));
            parseVal.add(jsonObject.getString("file_bimbingan"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<String> detailLombaParse(){
        JSONObject jsonObject;
        List<String> parseVal = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            parseVal.add(jsonObject.getString("nama_lomba"));
            parseVal.add(jsonObject.getString("penyelenggara"));
            parseVal.add(jsonObject.getString("kategori"));
            parseVal.add(jsonObject.getString("hadiah"));
            parseVal.add(jsonObject.getString("syarat"));
            parseVal.add(jsonObject.getString("deskripsi_lomba"));
            parseVal.add(jsonObject.getString("file_profpic_lomba"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<ListPengaturanTimObjek> listTimParse(String param){
        JSONObject jsonObject;
        List <ListPengaturanTimObjek> parseVal = new ArrayList<>();
        if(param.equals("my_team")){
            try {
                jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("my_team");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jRow = jsonArray.getJSONObject(i);
                    ListPengaturanTimObjek listPengaturanTimObjek = new ListPengaturanTimObjek(
                            jRow.getString("id_tim"),
                            jRow.getString("nama_tim"),
                            jRow.getString("maksimal_anggota"),
                            jRow.getString("deskripsi_tim"),
                            jRow.getString("file_fotoprofil_tim"),
                            "ketua"
                    );
                    parseVal.add(listPengaturanTimObjek);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(param.equals("joined_team")){
            try {
                jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("joined_team");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jRow = jsonArray.getJSONObject(i);
                    JSONObject rowTim = jRow.getJSONObject("tim");
                    ListPengaturanTimObjek listPengaturanTimObjek = new ListPengaturanTimObjek(
                            rowTim.getString("id_tim"),
                            rowTim.getString("nama_tim"),
                            rowTim.getString("maksimal_anggota"),
                            rowTim.getString("deskripsi_tim"),
                            rowTim.getString("file_fotoprofil_tim"),
                            "anggota"
                    );
                    parseVal.add(listPengaturanTimObjek);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jRow = jsonArray.getJSONObject(i);
                    ListPengaturanTimObjek listPengaturanTimObjek = new ListPengaturanTimObjek(
                            jRow.getString("id_tim"),
                            jRow.getString("nama_tim"),
                            jRow.getString("maksimal_anggota"),
                            jRow.getString("deskripsi_tim"),
                            jRow.getString("file_fotoprofil_tim"),
                            "other"
                    );
                    parseVal.add(listPengaturanTimObjek);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return parseVal;
    }
    public List<String> detailTimParse(String param){
        JSONObject jsonObject;
        List<String> parseval = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            if(param.equals("maindata")){
                parseval.add(jsonObject.getString("nama_tim"));
                parseval.add(jsonObject.getString("deskripsi_tim"));
                parseval.add(jsonObject.getString("maksimal_anggota"));
                parseval.add(jsonObject.getString("file_fotoprofil_tim"));
                parseval.add(jsonObject.getString("file_fotolomba"));
            }
            else {
                JSONArray anggotaTim = jsonObject.getJSONArray("nama_anggota");
                for(int i=0;i<anggotaTim.length();i++){
                    parseval.add(anggotaTim.getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseval;
    }
    public List<String> detailUserParse(){
        JSONObject jsonObject;
        JSONArray jsonArray;
        List<String>parseVal = new ArrayList<>();
        try {
//            jsonObject = new JSONObject(json);
            jsonArray = new JSONArray(json);
            jsonObject = jsonArray.getJSONObject(0);
            parseVal.add(jsonObject.getString("nama"));
            parseVal.add(jsonObject.getString("jurusan"));
            parseVal.add(jsonObject.getString("password"));
//            parseVal.add(jsonObject.getString("angkatan"));
//            parseVal.add(jsonObject.getString("file_profpic"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<DosenObjek> listDosen(){
        JSONObject jsonObject;
        List<DosenObjek> parseVal = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            for(int i=0;i<data.length();i++){
                JSONObject jRow = data.getJSONObject(i);
                DosenObjek dosenObjek = new DosenObjek(
                        jRow.getString("id_dosbing"),
                        jRow.getString("nama_dosbing"),
                        jRow.getString("jurusan_dosbing"),
                        jRow.getString("nohp_dosbing")
                );
                parseVal.add(dosenObjek);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<RequestCalonObjek> listRequestCalon(){
        JSONArray jsonArray;
        List<RequestCalonObjek> parseVal = new ArrayList<>();

        try {
            jsonArray = new JSONArray(json);
            for (int i =0;i<jsonArray.length();i++){
                JSONObject jRow = jsonArray.getJSONObject(i);
                JSONObject jUser = jRow.getJSONObject("user");
                RequestCalonObjek requestCalonObjek = new RequestCalonObjek(
                        jUser.getString("nrp"),
                        jUser.getString("nama")
                );
                parseVal.add(requestCalonObjek);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<BimbinganObjek> listBimbingan(){
        JSONObject jsonObject;
        List<BimbinganObjek> parseVal = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i=0;i<data.length();i++){
                JSONObject jRow = data.getJSONObject(i);
                BimbinganObjek bimbinganObjek = new BimbinganObjek(
                        jRow.getString("id_bimbingan"),
                        jRow.getString("tanggal_bimbingan"),
                        jRow.getString("comment_bimbingan")
                );
                parseVal.add(bimbinganObjek);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<ListLombaObjek> listLomba(){
        JSONObject jsonObject;
        List<ListLombaObjek> parseVal = new ArrayList<>();

        try {
            JSONArray data = new JSONArray(json);
            for(int i=0;i<data.length();i++){
                JSONObject jRow = data.getJSONObject(i);
                ListLombaObjek listLombaObjek = new ListLombaObjek(
                        jRow.getString("id_lomba"),
                        jRow.getString("nama_lomba"),
                        jRow.getString("penyelenggara"),
                        jRow.getString("kategori"),
                        jRow.getString("syarat"),
                        jRow.getString("deskripsi_lomba"),
                        jRow.getString("hadiah")
                );
                parseVal.add(listLombaObjek);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
    public List<ListTimObjek> listTimIkutSerta(){
        List <ListTimObjek> parseVal = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jRow = jsonArray.getJSONObject(i);
                JSONObject jRowTim = jRow.getJSONObject("tim");
                ListTimObjek listTimObjek = new ListTimObjek(
                        jRowTim.getString("id_tim"),
                        jRowTim.getString("nama_tim")
                );
                parseVal.add(listTimObjek);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
}
