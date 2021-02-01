package com.example.memomap;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ClaseEscritura {
    private boolean todoOk;
    private BufferedWriter bw;
    private File sd = Environment.getExternalStorageDirectory();

    public ClaseEscritura(){}

    public boolean escribirMemInterna(Activity activity, String nombreArchivo, String json){
        todoOk = false;
        try {
            OutputStreamWriter output = new OutputStreamWriter(activity.openFileOutput(nombreArchivo + ".txt",Context.MODE_PRIVATE));
            output.write(json);
            output.flush();
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

    // TODO: 29/01/2021 No se sabe si no lee o no escribe, hay q mirar donde falla 
    public boolean escribirMemCompartida(String nombreArchivo, String json){
        todoOk = false;
        System.out.println("============================");
        System.out.println("escritura AbsolutePath: " + sd.getAbsolutePath());
        System.out.println("escritura: " + sd.getAbsolutePath());
        System.out.println("============================");
        System.out.println("============================");
        System.out.println("Puede escribir??" + sd.canWrite());
        System.out.println("============================");
        System.out.println("============================");
        System.out.println("Puede escribir??" + sd.canWrite());
        System.out.println("============================");
        if(sd.canWrite()){
            try {
                System.out.println("============================");
                System.out.println("escribirMemCompartida: Va  a escribir");
                System.out.println("============================");
                File f = new File(sd, nombreArchivo + ".txt");
                bw = new BufferedWriter(new FileWriter(f));
                bw.write(json);
                System.out.println("============================");
                System.out.println("escribirMemCompartida: Archivo escrito");
                System.out.println("============================");
                bw.flush();
                bw.close();
                todoOk = true;
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo");
                e.printStackTrace();
            }
        }
        return todoOk;
    }
    public boolean escribirMemRAW(){
        return todoOk;
    }
    public boolean escribirMemSD(){
        return todoOk;
    }
}
