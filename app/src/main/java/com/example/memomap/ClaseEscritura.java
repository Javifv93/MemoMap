package com.example.memomap;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**Clase que realiza los procesos de escritura de archivos*/
public class ClaseEscritura {
    private boolean todoOk;
    private File sd = Environment.getExternalStorageDirectory();

    /**Constructor*/
    public ClaseEscritura(){}
    /**Método para escribir en la memoria interna del teléfono*/
    public boolean escribirMemInterna(Activity activity, String nombreArchivo, String json) {
        todoOk = false;
        try {
            OutputStreamWriter output = new OutputStreamWriter(activity.openFileOutput(nombreArchivo + ".txt", Context.MODE_PRIVATE));
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
    /**Método para escribir en la tarjeta de memoria externa*/
    public boolean escribirMemSD(Activity activity, String nombreArchivo,String json){
        File[] Memoria = ContextCompat.getExternalFilesDirs(activity.getApplicationContext(), null);
        File ruta = Memoria[0];
        File file = new File(ruta.getAbsolutePath(), nombreArchivo + ".txt");
        try {
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file));
            output.write(json);
            output.close();
            todoOk = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todoOk;
    }
}
