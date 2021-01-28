package com.example.memomap;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ControladorRW {
    private ClaseLectura lectura;
    private ClaseEscritura escritura;
    public enum Memoria {INTERNA, EXTERNA, COMPARTIDA, RAW, SD}

    public ControladorRW(){};

    public String leerArchivo(Activity activity, String registro_nota, Memoria tipoMemoria){
        lectura = new ClaseLectura();
        System.out.println("============================");
        System.out.println("leerArchivo "+ registro_nota);
        System.out.println("============================");
        String texto = "";
        try {
            switch (tipoMemoria){
                case INTERNA: texto = lectura.leerMemInterna(activity,registro_nota); break;
                case EXTERNA: texto = lectura.leerMemExterna(registro_nota); break;
                case COMPARTIDA: texto = lectura.leerMemCompartida(registro_nota); break;
                case RAW: texto = lectura.leerMemRAW(activity); break;
                case SD: texto = lectura.leerMemSD(activity, registro_nota); break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al acceder al archivo");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
        return texto;
    }
    public boolean escribirArchivo(Activity activity,String nombreArchivo, String json, Memoria tipoMemoria){
        boolean todoOk = false;
        escritura = new ClaseEscritura();
        switch (tipoMemoria){
            case INTERNA: {
                System.out.println("============================");
                System.out.println("escribirArchivo "+ json);
                System.out.println("============================");
                todoOk = escritura.escribirMemInterna(activity, nombreArchivo, json);
                break;
            }
            case EXTERNA: ; break;
            case COMPARTIDA: ; break;
            case RAW: break;
            case SD: break;
        }
        return todoOk;
    }
}
