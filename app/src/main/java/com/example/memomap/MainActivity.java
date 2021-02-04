package com.example.memomap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;

/**Clase de la actividad principal*/
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ImageButton mainMas;
    private ImageButton config;
    private ArrayList<String> listaNotas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**IMPLEMENTACIÓN DEL BORRADO DE LA LISTA DE NOTAS EN LAS SP*/
        limpiarSP();

        listView = (ListView) findViewById(R.id.main_listview);
        mainMas = (ImageButton) findViewById(R.id.main_mas);
        config = (ImageButton) findViewById(R.id.config);

        mainMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(MainActivity.this, Pagina.class);
                startActivity(intnt);
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(MainActivity.this, Config.class);
                startActivity(intnt);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String registro = obtenerRegistroSP();
        if(registro!=null)
        {
            AdaptadorLista adapter = new AdaptadorLista(MainActivity.this, registro);
            listView.setAdapter(adapter);
        }
        //seekAndDestroy();
    }
    /**Método que limpia el registro de notas de las SharedPreferences, por lo cual, aunque las notas creadas ya creadas no se borran de la memoria, dejan de aparecer reflejadas en la app*/
    protected void limpiarSP(){
        SharedPreferences sp = getSharedPreferences("registroNotas", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editor = sp.edit();
        sp_editor.clear().apply();
    }
    /**Método que devuelve la String de registro de las sharedprefenreces o null si no hay nada guardado*/
    protected String obtenerRegistroSP(){
        SharedPreferences sp = getSharedPreferences("registroNotas",Context.MODE_PRIVATE);
        String registro = sp.getString("registro",null);
        sp_a_ArrayList(registro);
        return registro;
    }
    /**Convierte el registro de las SharedPreferences en una lista de objetos Nota*/
    protected void sp_a_ArrayList(String registro){
        listaNotas = new ArrayList<String>();
        if(registro!=null){
            String[] registro_split = registro.split("%");
            for(int x=0;x<registro_split.length;x++){
                listaNotas.add(registro_split[x]);
            }
        }
    }
    // TODO: 04/02/2021 Este método debe borrar de la memoria las notas creadas a la vez que se limpiar de las SP
    protected void seekAndDestroy(){
    }
}