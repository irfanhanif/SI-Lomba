package com.example.fandyaditya.silomba;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fandy Aditya on 5/1/2017.
 */

public class ParseJSON {
    private String json;

    ParseJSON(String json) {
        this.json = json;
    }

    public String registerParse(){
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
}
