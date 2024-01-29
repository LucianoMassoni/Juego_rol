package com.mycompany.app;

public class Elfo extends Personaje {
    @Override
    public double atacar(){
        return ((((valorDeAtaque()*efectividadDeDisparo())-defensa())/500)*10)*1.05;
    }
}
