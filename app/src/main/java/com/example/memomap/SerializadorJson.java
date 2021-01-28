package com.example.memomap;

import com.google.gson.Gson;

public class SerializadorJson {

    public SerializadorJson(){}
    /**Convierte un objeto en una String con formato json*/
    public String objeto_to_json(Object objeto){
        Gson gson = new Gson();
        String json = gson.toJson(objeto);
        return json;
    }
    public Nota json_to_objeto(String json){
        Gson gson = new Gson();
        Nota nota = gson.fromJson(json, Nota.class);
        return nota;
    }
}
