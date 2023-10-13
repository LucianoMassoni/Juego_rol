package com.mycompany.app.managers;

import com.mycompany.app.Jugador;
import com.mycompany.app.Personaje;
import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;

public class CombateManager {
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

        int ronda = 0;
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
            ronda++;
        } while (cartaJugador2.isSeleccionable() && cartaJugador1.isSeleccionable() && ronda < 7);

        if(cartaJugador1.isSeleccionable() && cartaJugador2.isSeleccionable()){
            System.out.println("Se terminó la ronda. Las cartas vuelven al mazo");
        }
        else if (cartaJugador1.isSeleccionable()) {
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
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        PersonajeManager.crearCartas(jugador1);
        PersonajeManager.crearCartas(jugador2);

        int turno = (int) (Math.random() * 100 + 1);
        while (PersonajeManager.cartasDisponibles(jugador1) && PersonajeManager.cartasDisponibles(jugador2)) {
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
        if (PersonajeManager.cartasDisponibles(jugador1)) {
            if (!PersonajeManager.cartasDisponibles(jugador2)){
                System.out.println("El jugador " + jugador1.getNombre() + " es el ganador!!!");
                System.out.println("estas son las cartas que le quedaron Y SUBEN DE NIVEL");
            }
            for (Personaje pj : jugador1.getPersonajes()) {
                if (pj.isSeleccionable()) {
                    pj.setNivel(pj.getNivel() + 1);
                    System.out.println(pj.toString());
                }
            }
        } else {
            if (!PersonajeManager.cartasDisponibles(jugador1)){
                System.out.println("El jugador " + jugador2.getNombre() + " es el ganador!!!");
                System.out.println("estas son las cartas que le quedaron Y SUBEN DE NIVEL!!!");
            }
            for (Personaje pj : jugador2.getPersonajes()) {
                if (pj.isSeleccionable()) {
                    pj.setNivel(pj.getNivel() + 1);
                    System.out.println(pj.toString());
                }
            }
        }
    }
}
