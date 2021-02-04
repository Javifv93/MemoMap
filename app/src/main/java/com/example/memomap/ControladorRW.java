package com.example.memomap;

import android.app.Activity;
import java.io.FileNotFoundException;
import java.io.IOException;

/**Controlador entre las clases de lectura y escritura y la vista*/
public class ControladorRW {
    private ClaseLectura lectura;
    private ClaseEscritura escritura;
    public enum Memoria {INTERNA, SD}

    /**Constructor*/
    public ControladorRW(){};

    /**Recibe una petición de lectura y en base al tipo de memoria definida la redirige al método de la clase de lectura para ese tipo de memoria*/
    public String leerArchivo(Activity activity, String registro_nota, Memoria tipoMemoria){
        lectura = new ClaseLectura();
        System.out.println("============================");
        System.out.println("leerArchivo "+ registro_nota);
        System.out.println("============================");
        String texto = "";
        try {
            switch (tipoMemoria){
                case INTERNA: texto = lectura.leerMemInterna(activity,registro_nota); break;
                case SD: texto = lectura.leerMemSD(activity, registro_nota);
                break;
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
    /**Recibe una petición de escritura y en base al tipo de memoria definida la redirige al método de la clase de escritura para ese tipo de memoria*/
    public boolean escribirArchivo(Activity activity,String nombreArchivo, String json, Memoria tipoMemoria){
        boolean todoOk = false;
        escritura = new ClaseEscritura();
        switch (tipoMemoria){
            case INTERNA: todoOk = escritura.escribirMemInterna(activity, nombreArchivo, json); break;
            case SD: todoOk = escritura.escribirMemSD(activity, nombreArchivo, json); break;
        }
        return todoOk;
    }
}
