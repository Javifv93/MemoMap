package com.example.memomap;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ClaseEscritura {
    private boolean todoOk;
    private boolean sdDisponible = false;
    private boolean sdAccesoEscritura = false;
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
    public boolean escribirMemExterna(String nombreArchivo, String json){

        /*todoOk=false;
        System.out.println("============================");
        System.out.println("Vamos a ver si estan llegando bien los datos:\r nombreArchivo: "+ nombreArchivo+"\rjosn: "+json);
        System.out.println("============================");
        File dir = new File("/storage/emulated/1");
        System.out.println("============================");
        System.out.println("A ver si guarda bien el directorio: "+"/storage/emulated/1" + "/dat");
        System.out.println("============================");
        boolean vaono;
        vaono = dir.mkdir();
        System.out.println("============================");
        System.out.println("/" + nombreArchivo+".txt");
        System.out.println("============================");
        File file = new File (dir,"//" + nombreArchivo+".txt");
        try {
            file.createNewFile();
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file));
            output.write(json);
            output.flush();
            output.close();
            todoOk = true;
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
            e.printStackTrace();
        }*/

        return todoOk;
    }

    // TODO: 29/01/2021 El lector de ClaseLectura no funciona
    /*public boolean escribirMemCompartida(String nombreArchivo, String json){
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
    }*/
    public boolean escribirMemRAW(){
        return todoOk;
    }
    public boolean escribirMemSD(Activity activity, String nombreArchivo,String json){
        EstadoSD();
        System.out.println("============================");
        System.out.println("sdDisponible: "+ sdDisponible+"\rsdAccesoEscritura: "+sdAccesoEscritura);
        System.out.println("============================");
        File[] Memoria = ContextCompat.getExternalFilesDirs(activity.getApplicationContext(), null);
        File ruta = Memoria[0];
        File file = new File(ruta.getAbsolutePath(), nombreArchivo + ".txt");
        try {
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file));
            output.write(json);
            output.close();
            todoOk = true;
            System.out.println("============================");
            System.out.println("TERMINO");
            System.out.println("============================");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todoOk;
    }
    public void EstadoSD(){
        String cadena = "";
        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible = true;
            sdAccesoEscritura = true;
        }
        else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible = true;
            sdAccesoEscritura = false;
        }
        else{
            sdDisponible = false;
            sdAccesoEscritura = false;
        }
    }
}
