package com.example.fandyaditya.silomba;

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
            JSONObject statusSuccess = jsonObject.getJSONObject("status");
            parseVal = statusSuccess.getString("status");
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
        List<String>parseVal = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            parseVal.add(jsonObject.getString("nama"));
            parseVal.add(jsonObject.getString("jurusan"));
            parseVal.add(jsonObject.getString("angkatan"));
            parseVal.add(jsonObject.getString("file_profpic"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseVal;
    }
}
