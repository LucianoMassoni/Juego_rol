package com.mycompany.app;

public class Humano extends Personaje{
    @Override
    public double atacar(){
        return (((valorDeAtaque()*efectividadDeDisparo())-defensa())/500)*10;

    }
}
