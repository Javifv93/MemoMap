package com.example.memomap;

import android.app.Activity;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClaseLectura {
    private BufferedReader bw;
    private boolean sdDisponible;
    private boolean sdAcceseoEscritura;
    private File sd;
    private Resources recurso;
    private String texto;

    public ClaseLectura(){}

    public String leerMemInterna(Activity activity, String registro_nota) throws IOException {
        texto = "";
        System.out.println("============================");
        System.out.println("leerMemInterna "+ registro_nota);
        System.out.println("============================");
        bw = new BufferedReader(new InputStreamReader(activity.openFileInput(registro_nota + ".txt")));
        String linea = null;
        if((linea = bw.readLine())!=null){
            texto += linea;
        }
        bw.close();
        System.out.println("============================");
        System.out.println("leerMemInterna "+ registro_nota);
        System.out.println("============================");
        return texto;
    }
    public String leerMemExterna(String registro_nota){
        return texto;
    }
    public String leerMemCompartida(String registro_nota){
        return texto;
    }
    public String leerMemRAW(Activity activity){
        return texto;
    }
    public String leerMemSD(Activity activity, String registro_nota){
        return texto;
    }
}
