package com.mycompany.app.managers;

import com.mycompany.app.Jugador;
import com.mycompany.app.Personaje;
import com.mycompany.app.PersonajeBuilder;
import com.mycompany.app.Scanners;
import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class PersonajeManager {
    public static void crearPersonaje(List<Personaje> listaDePersonajes) throws CantidadMaximaDeIntentosException {
        Personaje personaje;
        PersonajeBuilder personajeBuilder = new PersonajeBuilder();

        personaje = personajeBuilder.withRaza()
                .withNombre()
                .withApodo()
                .withFechaDeNacimiento()
                .withSalud()
                .withVelocidad()
                .withDestreza()
                .withFuerza()
                .withNivel()
                .withArmadura()
                .build();

        listaDePersonajes.add(personaje);
    }

    public static void crearPersonajeAleatorio(List<Personaje> listaDePersonajes) {
        Personaje personaje;
        PersonajeBuilder personajeBuilder = new PersonajeBuilder();

        personaje = personajeBuilder.randomBuild();

        listaDePersonajes.add(personaje);
    }

    public static boolean cartasDisponibles(Jugador jugador) {
        int contador = 0;
        for (Personaje pj : jugador.getPersonajes()) {
            if (!pj.isSeleccionable()) {
                contador++;
            }
        }
        return contador != 3;
    }

    //Pase la creacion de las cartas a esta funcion para que no salga por pantalla.
    public static void crearCartas(Jugador jugador) throws CantidadMaximaDeIntentosException {
        Scanners sc = new Scanners();

        //Cambio el System.setOut para que no se guarde en el log
        FlujoOutputManager.setOutputSoloConsola();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        System.out.print("Bienvenido jugador\nIngrese su nombre: ");
        jugador.setNombre(sc.scannerString());

        System.out.println(jugador.getNombre() + " vamos a crear tus 3 personajes");
        for (int i = 0; i < 3; i++) {
            MenuManager.menuCreacionPersonaje(jugador);
        }

        //Vuelvo a cambiar el System.setOut para que ahora si se guarde en el log
        FlujoOutputManager.setOutputGuardaEnLog();
    }
}
