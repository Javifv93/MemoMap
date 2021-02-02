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

import java.util.ArrayList;

public class AdaptadorLista extends ArrayAdapter<String> {
    private Activity activity;
    private ArrayList<Nota> listaNotas;
    private ArrayList<String> registroNotas;
    final ViewHolder viewHolder = new ViewHolder();
    private ControladorRW.Memoria memoria = ControladorRW.Memoria.EXTERNA;
    View vista;

    public AdaptadorLista(Activity activity, String registro_sp){
        super(activity, R.layout.nota);
        this.activity = activity;
        this.registroNotas = new ArrayList<String>();
        this.listaNotas = new ArrayList<Nota>();
        String[] registro_split = registro_sp.split("%");
        for(int x=0;x<registro_split.length;x++){
            this.registroNotas.add(registro_split[x]);
            listaNotas.add(obtenerNota(registro_split[x]));
        }
    }

    static class ViewHolder{
        protected ConstraintLayout notaFondo;
        protected TextView notaNombre;
        protected ImageButton notaEdit;
        protected ImageButton notaBorrar;
    }

    public int getCount(){
        return listaNotas.size();
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

        viewHolder.notaNombre.setText(listaNotas.get(position).getTitulo());

        /**Listener que envia los atributos id, titulo y texto de la Nota asignada a esta posición en listaNotas a Pagina.java, en la cual se visualizará*/
        viewHolder.notaFondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(v.getContext(),Pagina.class);
                intnt.putExtra("id", listaNotas.get(position).getId());
                intnt.putExtra("titulo",listaNotas.get(position).getTitulo());
                intnt.putExtra("texto",listaNotas.get(position).getTexto());
                System.out.println("============================");
                System.out.println("onClick adaptador: -id: "+ listaNotas.get(position).getId() +
                        " titulo: "+ listaNotas.get(position).getTitulo() + " texto: " + listaNotas.get(position).getTexto());
                System.out.println("============================");
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
    /**Devuelve un objeto Nota en base a la String con los datos proporcionados por las SP*/
    protected Nota obtenerNota(String registro_nota){
        ControladorRW crw = new ControladorRW();
        String texto = crw.leerArchivo(activity,registro_nota, memoria);
        Nota nota = new Nota(Integer.parseInt(registro_nota.split("#")[1]),registro_nota.split("#")[0], texto);
        return nota;
    }
}
// TODO: 29/01/2021 https://www.sgoliver.net/blog/ficheros-en-android-i-memoria-interna/ Para las preguntas
// TODO: 29/01/2021 https://developer.android.com/training/data-storage
