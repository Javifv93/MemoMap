package com.example.memomap;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClaseLectura {
    private BufferedReader br;
    private boolean sdDisponible;
    private boolean sdAcceseoEscritura;
    private File sd = Environment.getExternalStorageDirectory();
    private Resources recurso;
    private String texto;

    public ClaseLectura(){}

    public String leerMemInterna(Activity activity, String registro_nota) throws IOException {
        texto = "";
        System.out.println("============================");
        System.out.println("leerMemInterna "+ registro_nota);
        System.out.println("============================");
        br = new BufferedReader(new InputStreamReader(activity.openFileInput(registro_nota + ".txt")));
        String linea = null;
        while((linea = br.readLine())!=null){
            texto += linea;
        }
        br.close();
        System.out.println("============================");
        System.out.println("leerMemInterna "+ registro_nota);
        System.out.println("============================");
        return texto;
    }
    public String leerMemExterna(String registro_nota){
        return texto;
    }
    // TODO: 29/01/2021 No se sabe si no lee o no escribe, hay q mirar donde falla
    public String leerMemCompartida(String registro_nota) throws IOException {
        System.out.println("============================");
        System.out.println("lectura AbsolutePath: " + sd.getAbsolutePath());
        System.out.println("lectura: " + sd.getAbsolutePath());
        System.out.println("============================");
        texto = "";
        if(sd.canRead()){
            File f = new File(sd, registro_nota + ".txt");
            if(f.exists()){
                br = new BufferedReader(new FileReader(f));
                String linea = null;
                while ((linea = br.readLine())!=null){
                    texto += linea;
                }
                br.close();
            }
        }
        System.out.println("============================");
        System.out.println("lectura textp:" + texto);
        System.out.println("============================");
        return texto;
    }
    public String leerMemRAW(Activity activity){
        return texto;
    }
    public String leerMemSD(Activity activity, String registro_nota){
        return texto;
    }
}
