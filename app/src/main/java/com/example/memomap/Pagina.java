package com.example.memomap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Pagina extends AppCompatActivity {
    private EditText titulo;
    private EditText texto;
    private Button guardar;
    private Nota notaActiva;
    private ControladorRW.Memoria memoria = ControladorRW.Memoria.COMPARTIDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina);

        titulo = (EditText) findViewById(R.id.pagina_titulo);
        texto = (EditText) findViewById(R.id.pagina_texto);
        guardar = (Button) findViewById(R.id.pagina_guardar);

        recibirIntent(savedInstanceState);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((titulo.getText().toString()!=null)
                        &&(titulo.getText().toString().length()>0)) {
                    guardarNota(titulo.getText().toString(), texto.getText().toString());
                    finish();
                }
                else {
                    Toast tostada = Toast.makeText(Pagina.this,"Introduce el título de la nota",Toast.LENGTH_SHORT);
                    tostada.show();
                }
            }
        });
    }
    /**Recibe el intent con los datos de la Nota en caso de que se haya pulsado encima de una en el listView, y settea los datos de los EditText con los atributos de la nota*/
    protected void recibirIntent(Bundle savedInstanceState) {
        SerializadorJson serializador = new SerializadorJson();
        if(getIntent().getIntExtra("id",-1)!=-1){
            if(getIntent().getStringExtra("titulo")!=null){
                if(getIntent().getStringExtra("texto")!=null){
                    /*System.out.println("===========================================================================");
                    System.out.println("id: "+getIntent().getIntExtra("id",-1) +
                            "titulo: " + getIntent().getStringExtra("titulo") + "texto: " + serializador.json_to_objeto(getIntent().getStringExtra("texto")).getTexto());
                    System.out.println("===========================================================================");*/
                    notaActiva = new Nota(getIntent().getIntExtra("id",-1),getIntent().getStringExtra("titulo"),getIntent().getStringExtra("texto"));
                    titulo.setText(notaActiva.getTitulo());
                    System.out.println("============================");
                    System.out.println("recibirIntent "+ notaActiva.getTexto());
                    System.out.println("============================");
                    System.out.println("============================");
                    System.out.println("recibirIntent "+ serializador.objeto_to_json(notaActiva.getTexto()));
                    System.out.println("============================");
                    texto.setText(serializador.json_to_objeto(notaActiva.getTexto()).getTexto());
                }
            }
        }
    }
    /**Crea un objeto Nota, lo convierte a json por medio del SerializadorJson y crea un
     * archivo .txt con un nombre generado aleatoriamente*/
    protected void guardarNota(String titulo, String texto){
        Nota nota;
        if(notaActiva!=null){
            notaActiva.setTitulo(titulo);
            notaActiva.setTexto(texto);
            nota = notaActiva;
        }
        else{
            nota = new Nota(titulo,texto);
        }
        SerializadorJson serializador = new SerializadorJson();

        String json = serializador.objeto_to_json(nota);

        if(escribirFichero(json, nota.generarNombreArchivo())==true){
            guardarRegistroSP(nota.generarNombreArchivo());
        }
    }
    /**Crea el archivo en base al nombre y escribe la cadena json en el*/
    protected boolean escribirFichero(String json, String nombreArchivo){
        ControladorRW crw = new ControladorRW();
        return crw.escribirArchivo(Pagina.this, nombreArchivo, json, memoria);
    }
    /**Guarda en las SharedPreferences una String con los nombres de archivos concatenados*/
    protected void guardarRegistroSP(String nombreArchivo){
        SharedPreferences sp = getSharedPreferences("registroNotas",Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editor = sp.edit();

        String registro = sp.getString("registro",null);
        if(registro!=null){
            sp_editor.putString("registro",registro + nombreArchivo +"%");
        }
        else{
            sp_editor.putString("registro",nombreArchivo + "%");
        }
        sp_editor.commit();
    }
}
