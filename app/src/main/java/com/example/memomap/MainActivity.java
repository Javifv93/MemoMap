package com.example.memomap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
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

        pruebasjson();

        listView = (ListView) findViewById(R.id.main_listview);
        mainMas = (ImageButton) findViewById(R.id.main_mas);

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
    void pruebasjson(){
        Gson gson = new Gson();
        Nota nota = new Nota("Manolo","hola mundo");
        String json = gson.toJson(nota);
        System.out.println(json);
    }
}