package com.example.memomap;

import com.google.gson.Gson;

public class SerializadorXML {

    public SerializadorXML(){}
    /**Convierte un objeto en una String con formato json*/
    public String objeto_to_json(Object objeto){
        Gson gson = new Gson();
        String json = gson.toJson(objeto);
        return json;
    }
}
