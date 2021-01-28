package com.example.memomap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.shadow.ShadowRenderer;
import com.google.gson.Gson;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ImageButton mainMas;
    private ArrayList<String> listaNotas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        limpiarSP();

        listView = (ListView) findViewById(R.id.main_listview);
        mainMas = (ImageButton) findViewById(R.id.main_mas);

        sp_a_ArrayList();

        mainMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(MainActivity.this, Pagina.class);
                startActivityForResult(intnt,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2)
        {

        }
    }
    protected void sp_a_ArrayList(){
        SharedPreferences sp = getSharedPreferences("registroNotas",Context.MODE_PRIVATE);
        String registro = sp.getString("registro",null);
        if(registro!=null){
            String[] registro_split = registro.split("%");
            for(int x=0;x<registro_split.length;x++){
                listaNotas.add(registro_split[x]);
            }
        }
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
        seekAndDestroy();
    }
    protected void limpiarSP(){
        SharedPreferences sp = getSharedPreferences("registroNotas", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editor = sp.edit();
        sp_editor.clear().apply();
    }
    /**Método que devuelve la String de registro de las sharedprefenreces o null si no hay nada guardado*/
    protected String obtenerRegistroSP(){
        SharedPreferences sp = getSharedPreferences("registroNotas",Context.MODE_PRIVATE);
        String registro = sp.getString("registro",null);
        return registro;
    }
    protected void seekAndDestroy(){

    }
}