package com.ejercicio.listado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;

    public Adaptador(Context contexto, String[][] datos) {
        this.contexto = contexto;
        this.datos = datos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int position) {
        return datos[position];
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(datos[position][0]);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.list_element, null);
        TextView tvTitulo = vista.findViewById(R.id.tvTitulo);
        TextView tvPlataforma = vista.findViewById(R.id.tvPlataforma);
        TextView tvAnio = vista.findViewById(R.id.tvAnio);
        ImageView ivGenero = vista.findViewById(R.id.ivGenero);

        tvTitulo.setText(contexto.getResources().getString(R.string.title_tv,datos[position][1]));
        tvPlataforma.setText(contexto.getResources().getString(R.string.platform_tv,datos[position][2]));
        tvAnio.setText(contexto.getResources().getString(R.string.year_tv,datos[position][3]));

        String genero = datos[position][4];

        //Cambiar dependiendo del género
        if (genero.equals("Adventure") || genero.equals("Aventura"))
            ivGenero.setImageResource(R.drawable.adventure);
        else if (genero.equals("Fighting") || genero.equals("Peleas"))
            ivGenero.setImageResource(R.drawable.fighting);
        else if (genero.equals("Horror"))
            ivGenero.setImageResource(R.drawable.horror);
        else if (genero.equals("Platformer") || genero.equals("Plataformas"))
            ivGenero.setImageResource(R.drawable.platformer);
        else if (genero.equals("Puzzle") || genero.equals("Rompecabezas"))
            ivGenero.setImageResource(R.drawable.puzzle);
        else if (genero.equals("Racing") || genero.equals("Carreras"))
            ivGenero.setImageResource(R.drawable.racing);
        else if (genero.equals("Rhythm") || genero.equals("Ritmo"))
            ivGenero.setImageResource(R.drawable.rhythm);
        else if (genero.equals("RPG"))
            ivGenero.setImageResource(R.drawable.rpg);
        else if (genero.equals("Shooter") || genero.equals("Disparos"))
            ivGenero.setImageResource(R.drawable.shooter);
        else if (genero.equals("Sports") || genero.equals("Deportes"))
            ivGenero.setImageResource(R.drawable.sports);
        else if (genero.equals("Strategy") || genero.equals("Estrategia"))
            ivGenero.setImageResource(R.drawable.strategy);

        return vista;
    }
}
