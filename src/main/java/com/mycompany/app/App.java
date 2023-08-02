package com.mycompany.app;

import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;


import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */


public class App
    /*
    * PersonajeBuilder constructor = new PersonajeBuilder();
        Personaje aver = new Humano();

        Personaje ut = new Elfo();
        ut.setArmadura(3);
        ut.setVelocidad(7);
        ut.setDestreza(5);
        ut.setFuerza(2);
        ut.setNivel(6);

        aver = constructor.withRaza()
                .withNombre()
                .withFechaDeNacimiento()
                .withSalud()
                .withVelocidad()
                .build();

        System.out.println(ut.atacar());
        System.out.println(aver.getSalud());
        System.out.println(aver.getVelocidad());

        Personaje ay;
        ay = constructor.randomBuild();
        System.out.println(ay.getSalud());
        System.out.println(ay.getVelocidad());
        System.out.println(ay.getDestreza());
        System.out.println(ay.getClass());
        System.out.println(aver.toString());
        * */


{
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

    public static void crearPersonajeAleatorio(List<Personaje> listaDePersonajes){
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
                System.out.println("Ingrese una opcion valida ("+contador+"/3)");
                if (contador == 3){
                    throw new CantidadMaximaDeIntentosException("Supero la cantidad maxima de intentos");
                }
            }
            else {
                break;
            }
        } while (true);

        if (opcion == 1){
            crearPersonaje(jugador.getPersonajes());
        } else {
            crearPersonajeAleatorio(jugador.getPersonajes());
        }


    }
    public static Personaje sorteoCartas(Jugador jugador){
        Personaje carta;
        do {
            carta = jugador.getPersonajes().get((int) (Math.random()*3));
        } while (!carta.isSeleccionable());
        return carta;
    }
    public static void combate(Jugador jugador1, Jugador jugador2){
        Personaje cartaJugador1;
        Personaje cartaJugador2;
        double ataque;
        int turno = 1;

        do {
            cartaJugador1 = jugador1.getPersonajes().get((int) (Math.random()*3));
        } while (!cartaJugador1.isSeleccionable());
        do {
            cartaJugador2 = jugador2.getPersonajes().get((int) (Math.random()*3));
        } while (!cartaJugador2.isSeleccionable());

        System.out.println("La carta del jugador " + jugador1.getNombre()+" es "+cartaJugador1.toString());
        System.out.println("La carta del jugador " + jugador2.getNombre()+" es "+cartaJugador2.toString());

        //todo
        // En el do-while deberia hacer algo de turnos para que se alterne.

        do {
            if (turno % 2 != 0){ //Enpieza atacando el jugador 1
                ataque = (double) (Math.round(cartaJugador1.atacar()*100))/100;
                if (ataque <= 0) {
                    System.out.println(cartaJugador1.getApodo()+" Erra el ataque");
                } else {
                    cartaJugador2.actualizarEstadoPersonaje(ataque);
                    System.out.println(cartaJugador1.getApodo()+" Infringe un daño de " + ataque + " a " + cartaJugador2.getApodo() +
                            " este quedando con una salud de " + cartaJugador2.getSalud());
                }
                turno++;
            } else { //Empieza atacando el jugador 2
                ataque = (double) (Math.round(cartaJugador2.atacar()*100))/100;
                if (ataque <= 0) {
                    System.out.println(cartaJugador2.getApodo()+" Erra el ataque");
                } else {
                    cartaJugador1.actualizarEstadoPersonaje(ataque);
                    System.out.println(cartaJugador2.getApodo()+" Infringe un daño de " + ataque + " a " + cartaJugador1.getApodo() +
                            " este quedando con una salud de " + cartaJugador1.getSalud());
                }
                turno++;
            }
        } while (cartaJugador2.isSeleccionable() && cartaJugador1.isSeleccionable());

        if (cartaJugador1.isSeleccionable()){
            System.out.println(jugador1.getNombre()+" gana la ronda con su carta " + cartaJugador1.getApodo());
            System.out.println(cartaJugador1.getApodo() + "recibe +10 en salud");
            cartaJugador1.setSalud(cartaJugador1.getSalud()+10);
        } else {
            System.out.println(jugador2.getNombre()+" gana la ronda con su carta " + cartaJugador2.getApodo());
            System.out.println(cartaJugador2.getApodo() + "recibe +10 en salud");
            cartaJugador2.setSalud(cartaJugador2.getSalud()+10);
        }
        System.out.println("----------------------------------------------------");
    }
    public static void main( String[] args ) throws CantidadMaximaDeIntentosException {
        System.out.println( "Hello World!" );
        Scanners sc = new Scanners();


        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        System.out.println("Increible juegaso de rol");
        System.out.print("Bienvenido jugador 1\nIngrese su nombre: ");
        jugador1.setNombre(sc.scannerString());
        System.out.print("Bienvenido jugador 2\nIngrese su nombre: ");
        jugador2.setNombre(sc.scannerString());

        System.out.println( jugador1.getNombre() +" vamos a crear tus 3 personajes");
        for (int i = 0; i < 3; i++) {
            menuCreacionPersonaje(jugador1);

        }
        System.out.println( jugador2.getNombre() +" vamos a crear tus 3 personajes");
        for (int i = 0; i < 3; i++) {
            menuCreacionPersonaje(jugador2);
        }



        int turno = (int) (Math.random()*100+1);
        do {
            if (turno % 2 == 0){
                System.out.println("el Jugador " + jugador1.getNombre() + " ataca");
                //En la funcion combate ataca primero el que se ingresa en la primer posicion.
                combate(jugador1, jugador2);
            } else {
                System.out.println("el Jugador " + jugador2.getNombre() + " ataca");
                combate(jugador2, jugador1);
            }
            turno++;
        } while(true);

    }

}
