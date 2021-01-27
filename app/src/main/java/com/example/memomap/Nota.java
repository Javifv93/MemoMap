package com.example.memomap;

import java.util.Random;

public class Nota {
    private int id;
    private String titulo;
    private String texto;

    public Nota(String titulo, String texto){
        setId();
        this.titulo=titulo;
        this.texto=texto;
    }
    public int getId(){return id;}
    public String getTitulo(){return titulo;}
    public String getTexto(){return texto;}
    /**setId genera un número aleatorio en privado y se invoca solo desde el constructor
     * de la clase, buscando generar ids únicos y inalterables*/
    private void setId() {
        int random = new Random().nextInt(999999);
        this.id = random;
    }
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setTexto(String texto) {this.texto = texto;}
    /**Devuelve un nombre de archivo basado en el título y el id*/
    public String generarNombreArchivo(){
        return titulo + Integer.toString(id);
    }
}
