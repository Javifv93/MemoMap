package com.example.memomap;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ClaseEscritura {
    private boolean todoOk;
    private BufferedWriter bw;

    public ClaseEscritura(){}

    public boolean escribirMemInterna(Activity activity, String nombreArchivo, String json){
        todoOk = false;
        try {
            OutputStreamWriter output = new OutputStreamWriter(activity.openFileOutput(nombreArchivo + ".txt",Context.MODE_PRIVATE));
            output.write(json);
            output.close();
            todoOk = true;
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
            e.printStackTrace();
        }
        return todoOk;
    }
    public boolean escribirMemExterna(){
        return todoOk;
    }
    public boolean escribirMemCompartida(){
        return todoOk;
    }
    public boolean escribirMemRAW(){
        return todoOk;
    }
    public boolean escribirMemSD(){
        return todoOk;
    }
}
