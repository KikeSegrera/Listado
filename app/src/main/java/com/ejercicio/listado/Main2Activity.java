package com.ejercicio.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList<Videogame> videojuegos = new ArrayList<Videogame>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = findViewById(R.id.lv);
        Bundle bundle = getIntent().getExtras();

        videojuegos = (ArrayList<Videogame>) bundle.getSerializable("videojuegos");
        int tamanio = videojuegos.size();

        String[][] datos = new String[tamanio][5];
        for(int i = 0; i < tamanio; i++) {
            datos[i][0] = videojuegos.get(i).getId();
            datos[i][1] = videojuegos.get(i).getTitulo();
            datos[i][2] = videojuegos.get(i).getPlataforma();
            datos[i][3] = videojuegos.get(i).getAnio();
            datos[i][4] = videojuegos.get(i).getGenero();
        }

        Adaptador adaptador = new Adaptador(this, datos);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, getResources().getString(R.string.toast_id,String.valueOf(id)), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
