package com.mycompany.app;

import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws CantidadMaximaDeIntentosException {
        System.out.println( "Hello World!" );
        Scanners sc = new Scanners();

        List<Personaje> jugador1 = new ArrayList<>();
        List<Personaje> jugador2 = new ArrayList<>();

        PersonajeBuilder constructor = new PersonajeBuilder();
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
    }

}
