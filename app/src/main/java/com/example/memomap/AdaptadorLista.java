package com.example.memomap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

/**Adaptador del ListView*/
public class AdaptadorLista extends ArrayAdapter<String> {
    private Activity activity;
    private ArrayList<Nota> listaNotas;
    private ArrayList<String> registroNotas;
    final ViewHolder viewHolder = new ViewHolder();
    private ControladorRW.Memoria memoria = ControladorRW.Memoria.INTERNA;
    View vista;

    /**Constructor. Obtiene la lista de notas de las SharedPreferences y las guarda en un ArrayList de Notas*/
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
        obtenerMemoria(activity);
    }
    /**Obtiene de las SharedPreferences el tipo de memoria donde va a guardar las notas*/
    private void obtenerMemoria(Activity activity) {
        SharedPreferences sp = activity.getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        switch (sp.getString("lp_memoria", "INTERNA")){
            case "Interna": memoria = ControladorRW.Memoria.INTERNA; break;
            case "Externa": memoria = ControladorRW.Memoria.SD; break;
        }
    }
    /**Define el ViewHolder con los elementos de nota.xml sin definir*/
    static class ViewHolder{
        protected ConstraintLayout notaFondo;
        protected TextView notaNombre;
        protected ImageButton notaEdit;
        protected ImageButton notaBorrar;
    }
    /**Devuelve el tamaño del ArrayList de Notas*/
    public int getCount(){
        return listaNotas.size();
    }
    /**Devuelve la posición de un item*/
    public long getItemId(int position){
        return position;
    }
    /**Devuelve la vista inflada de la nota*/
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
                activity.setResult(2,intnt);
                activity.startActivity(intnt);
            }
        });

        /* TODO: 25/01/2021 Al clickar en edit abrirá un menu con diversas opciones,
             cambiar nombre, color, posicion, etc*/
        viewHolder.notaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
