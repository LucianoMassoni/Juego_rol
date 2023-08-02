package com.mycompany.app;

import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;


import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */


public class App{

    //Todo hacer el printLog() y el borrarLog()
    public static void printLog(){

    }
    public static void borrarLog(){

    }
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

    public static void menuCreacionPersonaje(Jugador jugador) throws CantidadMaximaDeIntentosException {
        int opcion;
        int contador = 0;
        Scanners sc = new Scanners();

        do {
            System.out.println("Menu de creacion de personaje");
            System.out.println("1. Crear tus personajes");
            System.out.println("2. Crear tus personajes aleatoreamente");
            System.out.println("Elija una opcion: ");
            opcion = sc.scannerInt(sc.scannerString());
            if (opcion < 1 || opcion > 2) {
                contador++;
                System.out.println("Ingrese una opcion valida (" + contador + "/3)");
                if (contador == 3) {
                    throw new CantidadMaximaDeIntentosException("Supero la cantidad maxima de intentos");
                }
            } else {
                break;
            }
        } while (true);

        if (opcion == 1) {
            crearPersonaje(jugador.getPersonajes());
        } else {
            crearPersonajeAleatorio(jugador.getPersonajes());
        }
    }

    public static boolean cartasDisponibles(Jugador jugador) {
        int contador = 0;
        for (Personaje pj : jugador.getPersonajes()) {
            if (!pj.isSeleccionable()) {
                contador++;
            }
        }
        if (contador == 3) {
            return false;
        } else {
            return true;
        }
    }

    public static void rondaDeCombate(Jugador jugador1, Jugador jugador2) {
        Personaje cartaJugador1;
        Personaje cartaJugador2;
        double ataque;
        int turno = 1;

        do {
            cartaJugador1 = jugador1.getPersonajes().get((int) (Math.random() * 3));
        } while (!cartaJugador1.isSeleccionable());
        do {
            cartaJugador2 = jugador2.getPersonajes().get((int) (Math.random() * 3));
        } while (!cartaJugador2.isSeleccionable());

        System.out.println("La carta del jugador " + jugador1.getNombre() + " es " + cartaJugador1.toString());
        System.out.println("La carta del jugador " + jugador2.getNombre() + " es " + cartaJugador2.toString());

        //todo
        // En el do-while deberia hacer algo de turnos para que se alterne.

        do {
            if (turno % 2 != 0) { //Enpieza atacando el jugador 1
                ataque = (double) (Math.round(cartaJugador1.atacar() * 100)) / 100;
                if (ataque <= 0) {
                    System.out.println(cartaJugador1.getApodo() + " Erra el ataque");
                } else {
                    cartaJugador2.actualizarEstadoPersonaje(ataque);
                    System.out.println(cartaJugador1.getApodo() + " Infringe un daño de " + ataque + " a " + cartaJugador2.getApodo() +
                            " este quedando con una salud de " + cartaJugador2.getSalud());
                }
                turno++;
            } else { //Empieza atacando el jugador 2
                ataque = (double) (Math.round(cartaJugador2.atacar() * 100)) / 100;
                if (ataque <= 0) {
                    System.out.println(cartaJugador2.getApodo() + " Erra el ataque");
                } else {
                    cartaJugador1.actualizarEstadoPersonaje(ataque);
                    System.out.println(cartaJugador2.getApodo() + " Infringe un daño de " + ataque + " a " + cartaJugador1.getApodo() +
                            " este quedando con una salud de " + cartaJugador1.getSalud());
                }
                turno++;
            }
        } while (cartaJugador2.isSeleccionable() && cartaJugador1.isSeleccionable());

        if (cartaJugador1.isSeleccionable()) {
            System.out.println(jugador1.getNombre() + " gana la ronda con su carta " + cartaJugador1.getApodo());
            System.out.println(cartaJugador1.getApodo() + " recibe +10 en salud");
            cartaJugador1.setSalud(cartaJugador1.getSalud() + 10);
        } else {
            System.out.println(jugador2.getNombre() + " gana la ronda con su carta " + cartaJugador2.getApodo());
            System.out.println(cartaJugador2.getApodo() + " recibe +10 en salud ");
            cartaJugador2.setSalud(cartaJugador2.getSalud() + 10);
        }
        System.out.println("----------------------------------------------------");
    }

    public static void combate() throws CantidadMaximaDeIntentosException {
        Scanners sc = new Scanners();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();


        System.out.print("Bienvenido jugador 1\nIngrese su nombre: ");
        jugador1.setNombre(sc.scannerString());
        System.out.print("Bienvenido jugador 2\nIngrese su nombre: ");
        jugador2.setNombre(sc.scannerString());

        System.out.println(jugador1.getNombre() + " vamos a crear tus 3 personajes");
        for (int i = 0; i < 3; i++) {
            menuCreacionPersonaje(jugador1);

        }
        System.out.println(jugador2.getNombre() + " vamos a crear tus 3 personajes");
        for (int i = 0; i < 3; i++) {
            menuCreacionPersonaje(jugador2);
        }

        //Todo buscar la forma de clarear la pantalla
        int turno = (int) (Math.random() * 100 + 1);
        while (cartasDisponibles(jugador1) && cartasDisponibles(jugador2)) {
            if (turno % 2 == 0) {
                System.out.println("el Jugador " + jugador1.getNombre() + " ataca");
                //En la funcion combate ataca primero el que se ingresa en la primer posicion.
                rondaDeCombate(jugador1, jugador2);
            } else {
                System.out.println("el Jugador " + jugador2.getNombre() + " ataca");
                rondaDeCombate(jugador2, jugador1);
            }
            turno++;
        }
        if (cartasDisponibles(jugador1)) {
            System.out.println("El jugador " + jugador1.getNombre() + " es el ganador!!!");
            System.out.println("estas son las cartas que le quedaron");
            for (Personaje pj : jugador1.getPersonajes()) {
                if (pj.isSeleccionable()) {
                    System.out.println(pj.toString());
                }
            }
        } else {
            System.out.println("El jugador " + jugador2.getNombre() + " es el ganador!!!");
            System.out.println("estas son las cartas que le quedaron Y SUBEN DE NIVEL!!!");
            for (Personaje pj : jugador2.getPersonajes()) {
                if (pj.isSeleccionable()) {
                    pj.setNivel(pj.getNivel() + 1);
                    System.out.println(pj.toString());
                }
            }
        }
    }
    public static void main(String[] args) throws CantidadMaximaDeIntentosException {
        Scanners sc = new Scanners();
        int op;
        int contador = 0;


        System.out.println("Increible juegaso de rol");
        System.out.println("------------------------");
        do {
            System.out.println("          Menu");
            System.out.println("1. Jugar");
            System.out.println("2. Ver el log de partidas ya jugadas");
            System.out.println("3. Borrar el log");
            System.out.println("0. SALIR");
            op = sc.scannerInt(sc.scannerString());
            contador++;
            if (contador > 3){
                throw new CantidadMaximaDeIntentosException("Superó la cantidad maxima de intentos");
            }
        } while(op < 0 || op > 3);

        switch (op){
            case 1:{
                combate();
                break;
            }
            case 2:{
                printLog();
                break;
            }
            case 3:{
                borrarLog();
                break;
            }
        }
        System.out.println("Gracias vuelva prontos");

    }
}
