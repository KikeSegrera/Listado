package com.ejercicio.listado;

import java.io.Serializable;

public class Videogame implements Serializable {

    String id;
    String titulo;
    String plataforma;
    String anio;
    String genero;

    public Videogame(String id, String titulo, String plataforma, String anio, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.anio = anio;
        this.genero = genero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
