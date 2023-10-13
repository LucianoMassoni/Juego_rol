package com.mycompany.app;

public class Ogro extends Personaje {
    @Override
    public double atacar(){
        return ((((valorDeAtaque()*efectividadDeDisparo())-defensa())/500)*100)*1.1;
    }
}
