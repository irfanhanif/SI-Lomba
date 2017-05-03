package com.example.fandyaditya.silomba.ListLomba;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;

import java.util.List;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class ListLombaFragment extends Fragment {
    RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_lomba,container,false);
        rv = (RecyclerView)rootView.findViewById(R.id.list_lomba_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }
    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "someurl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<ListLombaObjek> listLombaObjekList = pj.listLomba();
        ListLombaAdapter listLombaAdapter = new ListLombaAdapter(getContext(),listLombaObjekList);
        rv.setAdapter(listLombaAdapter);
    }
}
