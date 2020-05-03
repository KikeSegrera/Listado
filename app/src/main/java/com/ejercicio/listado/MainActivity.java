package com.ejercicio.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTitulo, etPlataforma, etAnio;
    Spinner sGenero;
    Button btAniadir, btListado, btBorrar;

    int id = 0;

    ArrayList<Videogame> videojuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        etTitulo = findViewById(R.id.etTitulo);
        etPlataforma = findViewById(R.id.etPlataforma);
        etAnio = findViewById(R.id.etAnio);

        sGenero = findViewById(R.id.sGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sGenero.setAdapter(adapter);

        btAniadir = findViewById(R.id.btAniadir);
        btListado = findViewById(R.id.btListado);
        btBorrar = findViewById(R.id.btBorrar);

        btAniadir.setOnClickListener(this);
        btListado.setOnClickListener(this);
        btBorrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btAniadir:
                if(valida()){
                    //Log.d("DEPURACION", sGenero.getSelectedItem().toString());
                    Videogame videogameTmp = new Videogame(String.valueOf(++id),
                            etTitulo.getText().toString(),
                            etPlataforma.getText().toString(),
                            etAnio.getText().toString(),
                            sGenero.getSelectedItem().toString());
                    videojuegos.add(videogameTmp);
                    saveData();

                    etTitulo.setText("");
                    etPlataforma.setText("");
                    etAnio.setText("");
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.element_succes), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btListado:
                if(!videojuegos.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("videojuegos", videojuegos);

                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }else
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.array_error), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btBorrar:
                if(!videojuegos.isEmpty()) {
                    videojuegos.clear();
                    saveData();
                }
                break;
        }
    }

    private boolean valida() {

        boolean failed = false;

        if(etTitulo.getText().length() == 0) {
            etTitulo.requestFocus();
            etTitulo.setError(getResources().getString(R.string.error_empty));
            failed = true;
        }else if(!etTitulo.getText().toString().matches("[A-Za-z0-9 .:,!?\"']+")){
            etTitulo.requestFocus();
            etTitulo.setError(getResources().getString(R.string.error_invalid));
            failed = true;
        }

        if(etPlataforma.getText().length() == 0) {
            etPlataforma.requestFocus();
            etPlataforma.setError(getResources().getString(R.string.error_empty));
            failed = true;
        }else if(!etPlataforma.getText().toString().matches("[A-Za-z0-9 ]+")){
            etPlataforma.requestFocus();
            etPlataforma.setError(getResources().getString(R.string.error_invalid));
            failed = true;
        }

        if(etAnio.getText().length() != 4) {
            etAnio.requestFocus();
            etAnio.setError(getResources().getString(R.string.error_year));
            failed = true;
        }

        return !failed;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(videojuegos);
        editor.putString("videojuegos", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("videojuegos", null);
        Type type = new TypeToken<ArrayList<Videogame>>() {}.getType();
        videojuegos = gson.fromJson(json, type);

        if (videojuegos == null) {
            videojuegos = new ArrayList<Videogame>();
        }
    }
}
