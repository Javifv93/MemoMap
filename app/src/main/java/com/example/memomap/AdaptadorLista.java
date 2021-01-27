package com.example.memomap;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdaptadorLista extends ArrayAdapter<String> {
    private Activity activity;
    ArrayList<String> listaContactos;
    final ViewHolder viewHolder = new ViewHolder();
    View vista;

    public AdaptadorLista(Activity activity, ArrayList<String> listaContactos){
        super(activity, R.layout.nota);
        this.activity = activity;
        this.listaContactos = listaContactos;
    }

    static class ViewHolder{
        protected ConstraintLayout notaFondo;
        protected TextView notaNombre;
        protected ImageButton notaEdit;
        protected ImageButton notaBorrar;
    }

    public int getCount(){
        return listaContactos.size();
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent){
        View view = null;
        vista = convertView;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.nota, null);

        viewHolder.notaFondo = (ConstraintLayout) view.findViewById(R.id.nota_fondo);
        viewHolder.notaNombre = (TextView) view.findViewById(R.id.nota_nombre);
        viewHolder.notaEdit = (ImageButton) view.findViewById(R.id.nota_edit);
        viewHolder.notaBorrar = (ImageButton) view.findViewById(R.id.nota_borrar);

        String nombre = listaContactos.get(position);
        viewHolder.notaNombre.setText(nombre);

        // TODO: 25/01/2021 Al clickar en el elemento lo abrirá
        viewHolder.notaFondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = viewHolder.notaNombre.getText().toString();
                String texto = "";
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(activity.openFileInput(titulo+".txt")));
                    String linea = null;
                    while((linea = br.readLine()) != null) {
                        texto += linea;
                    }
                    br.close();
                } catch (FileNotFoundException e) {
                    Toast tostada = Toast.makeText(v.getContext(), "Error al acceder al archivo", Toast.LENGTH_SHORT);
                    tostada.show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast tostada = Toast.makeText(v.getContext(), "Error al leer el archivo", Toast.LENGTH_SHORT);
                    tostada.show();
                    e.printStackTrace();
                }
                Intent intnt = new Intent(v.getContext(),Pagina.class);
                intnt.putExtra("titulo",titulo);
                intnt.putExtra("texto",texto);
                activity.setResult(2,intnt);
                activity.startActivity(intnt);
            }
        });

        /* TODO: 25/01/2021 Al clickar en edit abrirá un menu con diversas opciones,
             cambiar nombre, color, posicion, etc*/
        viewHolder.notaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SelectorColor selectorColor = new SelectorColor();
//                selectorColor.colorear_spectrum(v);
//                viewHolder.notaFondo.setBackgroundColor(selectorColor.getColor());
            }
        });

        /* TODO: 25/01/2021 Al clickar en borrar te saldrá un alert dialog y te dejará borrar la nota */
        viewHolder.notaBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast tostada = Toast.makeText(v.getContext(), "Borrar", Toast.LENGTH_SHORT);
                tostada.show();
            }
        });
        return view;
    }

}
