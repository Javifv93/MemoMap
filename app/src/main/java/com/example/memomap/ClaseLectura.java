package com.example.memomap;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**Clase que realiza los procesos de lectura de archivos*/
public class ClaseLectura {
    private BufferedReader br;
    private File sd;
    private String texto;

    /**Constructor*/
    public ClaseLectura(){
        sd = Environment.getExternalStorageDirectory();
    }

    /**Método que lee de la memoria interna el archivo pedido por parámetro*/
    public String leerMemInterna(Activity activity, String registro_nota) throws IOException {
        texto = "";
        br = new BufferedReader(new InputStreamReader(activity.openFileInput(registro_nota + ".txt")));
        String linea = null;
        while((linea = br.readLine())!=null){
            texto += linea;
        }
        br.close();
        return texto;
    }
    /**Método que lee de la tarjeta SD el archivo pedido por parámetro*/
    public String leerMemSD(Activity activity, String registro_nota) throws IOException {
        texto = "";
        File[] Memoria = ContextCompat.getExternalFilesDirs(activity.getApplicationContext(), null);
        File ruta = Memoria[0];
        File file = new File(ruta.getAbsolutePath(), registro_nota + ".txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        texto = br.readLine();
        br.close();
        return texto;
    }
}
