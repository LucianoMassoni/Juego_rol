package com.mycompany.app.managers;

import com.mycompany.app.Jugador;
import com.mycompany.app.Scanners;
import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;
public class MenuManager {
    public static int menu(int contador) throws CantidadMaximaDeIntentosException {
        int op;
        Scanners sc = new Scanners();

        //Cambio el flujo para que no se guarde en el log
        FlujoOutputManager.setOutputSoloConsola();


        System.out.println("          Menu");
        System.out.println("1. Jugar");
        System.out.println("2. Ver el log de partidas ya jugadas");
        System.out.println("3. Borrar el log");
        System.out.println("0. SALIR");
        System.out.print("Ingrese su opcion: ");
        op = sc.scannerInt(sc.scannerString());
        //Si contador > 1 son 3 intentos, porque primero arranca el menu para sacar la opcion.
        if (contador > 1){
            throw new CantidadMaximaDeIntentosException("Superó la cantidad maxima de intentos");
        }

        //seteo el flujo para que lo guarde en el log
        FlujoOutputManager.setOutputGuardaEnLog();
        return op;
    }

    public static void menuCreacionPersonaje(Jugador jugador) throws CantidadMaximaDeIntentosException {
        int opcion;
        int contador = 0;
        Scanners sc = new Scanners();

        //Cambio el flujo para que no se guarde en el log
        FlujoOutputManager.setOutputSoloConsola();


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
            PersonajeManager.crearPersonaje(jugador.getPersonajes());
        } else {
            PersonajeManager.crearPersonajeAleatorio(jugador.getPersonajes());
        }

        //seteo el flujo para que lo guarde en el log
        FlujoOutputManager.setOutputGuardaEnLog();
    }
}
