package com.mycompany.app;

import java.time.LocalDate;


public abstract class Personaje {
    //Atributos
    private String nombre;
    private String apodo;
    private LocalDate fechaDeNacimiento;
    private int edad;
    private int salud;
    private boolean seleccionable = true;

    //Caracteristicas
    private int velocidad;
    private int destreza;
    private int fuerza;
    private int nivel;
    private int armadura;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public boolean isSeleccionable() {
        return seleccionable;
    }

    public void setSeleccionable(boolean seleccionable) {
        this.seleccionable = seleccionable;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    //funciones
    //Estos metodos del calculo de ataque me parece que deberian ser privados
    public double poderDeDisparo(){
        return this.destreza * this.fuerza * nivel;
    }

    public double efectividadDeDisparo(){
        int random = (int)(Math.random()*100+1);
        return (double) random/100;
    }

    public double valorDeAtaque(){
        return poderDeDisparo() * efectividadDeDisparo();
    }

    public double defensa(){
        return this.armadura * this.velocidad;
    }


    public double atacar(){
        return 0;
    }
    public void actualizarEstadoPersonaje(int salud){
        this.salud = salud;
        if (this.salud < 0) {
            seleccionable = false;
        }
    }

}
