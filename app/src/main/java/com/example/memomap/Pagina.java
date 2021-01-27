package com.example.memomap;

import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina);

        titulo = (EditText) findViewById(R.id.pagina_titulo);
        texto = (EditText) findViewById(R.id.pagina_texto);
        guardar = (Button) findViewById(R.id.pagina_guardar);

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
    /**Crea un objeto Nota, lo convierte a json por medio del SerializadorXML y crea un
     * archivo .txt con un nombre generado aleatoriamente*/
    protected void guardarNota(String titulo, String texto){
        Nota nota = new Nota(titulo,texto);
        SerializadorXML serializador = new SerializadorXML();

        String json = serializador.objeto_to_json(nota);

        escribirFichero(json, nota.generarNombreArchivo());
    }
    /**Crea el archivo en base al nombre y escribe la cadena json en el*/
    protected void escribirFichero(String json, String nombreArchivo){
        try {
            OutputStreamWriter output = new OutputStreamWriter(openFileOutput(nombreArchivo+".txt",Context.MODE_PRIVATE));
            output.write(json);
            output.close();
        } catch (FileNotFoundException e) {
            Toast tostada = Toast.makeText(Pagina.this,"Error al crear el archivo",Toast.LENGTH_SHORT);
            tostada.show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast tostada = Toast.makeText(Pagina.this,"Error al escribir en el archivo",Toast.LENGTH_SHORT);
            tostada.show();
            e.printStackTrace();
        }
    }
}
