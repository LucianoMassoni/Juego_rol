package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Personaje> personajes = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}
