package com.mycompany.app;

import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;

import com.github.javafaker.Faker;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class PersonajeBuilder {

    Scanners sc = new Scanners();
    private Personaje raza;
    private String nombre;
    private String apodo;
    private LocalDate fechaDeNacimiento;
    private int edad;
    private int salud;

    //Caracteristicas
    private int velocidad;
    private int destreza;
    private int fuerza;
    private int nivel;
    private int armadura;

    public PersonajeBuilder withRaza() throws CantidadMaximaDeIntentosException {
        int opPersonaje;
        int contador = 0;

        System.out.println("Creacion de personaje");
        do {
            System.out.println("1. Humano");
            System.out.println("2. Elfo");
            System.out.println("3. Ogro");
            System.out.print("Ingrese su raza: ");
            opPersonaje = sc.scannerInt(sc.scannerString());
            contador++;
            if (contador > 3){
                throw new CantidadMaximaDeIntentosException("Superó la cantidad maxima de intentos");
            }
        } while (opPersonaje < 1 || opPersonaje > 3);

        if (opPersonaje == 1){
            this.raza = new Humano();
        } else if (opPersonaje == 2) {
            this.raza = new Elfo();
        } else {
            this.raza = new Ogro();
        }
        return this;
    }
    public PersonajeBuilder withNombre() {
        //No se si esta bien hacer que el ingreso del dato este en la funcion del builder
        System.out.print("Ingrese el nombre del personaje: ");
        this.nombre = sc.scannerString();
        return this;
    }

    public PersonajeBuilder withApodo() {
        System.out.print("Ingrese el apodo del personaje: ");
        this.apodo = sc.scannerString();
        return this;
    }

    public PersonajeBuilder withFechaDeNacimiento() throws CantidadMaximaDeIntentosException {
        LocalDate fechaDeNacimiento = null;
        String fechaAuxString;
        int dia, mes, anno;
        int contador = 0;
        int contadorFecha = 0;
        boolean fechaCorrecta = false;

        do {
            System.out.println("Ingrese la fecha de nacimiento");

            do{
                contadorFecha++;
                if (contadorFecha > 1){ //Le resto 1 porque cuando entra ya vale 2
                    System.out.println("Error al ingresar un dato. ("+ (contadorFecha-1) +"/3)");
                }
                if (contadorFecha > 3){
                        throw new CantidadMaximaDeIntentosException("Supero la cantidad maxima de intentos");
                    }
                System.out.print("dia: ");
                fechaAuxString = sc.scannerString();
                dia = sc.scannerInt(fechaAuxString);
            }while(dia < 1 || dia > 31);

            //Reinicio el contador
            contadorFecha = 0;
            do {
                contadorFecha++;
                if (contadorFecha > 1){ //Le resto 1 porque cuando entra ya vale 2
                    System.out.println("Error al ingresar un dato. ("+ (contadorFecha-1) +"/3)");
                }
                if (contadorFecha > 3){
                    throw new CantidadMaximaDeIntentosException("Supero la cantidad maxima de intentos");
                }
                System.out.print("mes: ");
                fechaAuxString = sc.scannerString();
                mes = sc.scannerInt(fechaAuxString);
            }while (mes < 1 || mes > 12);

            contadorFecha = 0;
            do {
                contadorFecha++;
                if (contadorFecha > 1){ //Le resto 1 porque cuando entra ya vale 2
                    System.out.println("Error al ingresar un dato. ("+ (contadorFecha-1) +"/3)");
                }
                if (contadorFecha > 3){
                    throw new CantidadMaximaDeIntentosException("Supero la cantidad maxima de intentos");
                }
                System.out.print("anno: ");
                fechaAuxString = sc.scannerString();
                anno = sc.scannerInt(fechaAuxString);
                // Toma la fecha actual saca el año y le resta 300 para que no pueda ser mayor a 300 años
            }while (anno < (LocalDate.now().getYear() - 300) || anno > (LocalDate.now().getYear()));

            try {
                fechaDeNacimiento = LocalDate.of(anno, mes, dia);
                fechaCorrecta = true;
            } catch (DateTimeException dateTimeException){
                contador++;
                System.out.println("Debes ingresar una fecha valida. ("+contador+"/3)");
                if (contador >=3){
                    throw dateTimeException;
                }
            }
        } while (!fechaCorrecta);

        this.fechaDeNacimiento = fechaDeNacimiento;
        //No creo que invocar la funcion withEdad aca esté bien. Pero pasandole le fecha se calcula solo.
        withEdad(fechaDeNacimiento);
        return this;
    }

    //withEdad no devuelve nada y es privada para que no se pueda settear la edad y se calcule sola.
    private void withEdad(LocalDate fechaDeNacimiento)  {
        int edad;
        LocalDate hoy = LocalDate.now();

        Period periodo = Period.between(fechaDeNacimiento, hoy);
        edad = periodo.getYears();
        this.edad = edad;
    }

    public PersonajeBuilder withSalud() throws CantidadMaximaDeIntentosException {
        int salud;
        String saludString;
        boolean saludInvalida;
        int contador = 0;

        do {
            System.out.print("Ingrese la salud de su personaje: ");
            saludString = sc.scannerString();
            salud = sc.scannerInt(saludString);
            if (salud < 0 || salud > 100){
                saludInvalida = true;
                contador++;
                System.out.println("Ingrese una salud entre 0 y 100. Intento ("+contador+"/3)");
            }
            else {
                saludInvalida = false;
            }
            if (!saludInvalida){
                this.salud = salud;
                break;
            }
        } while(contador < 3);

        return this;
    }

    public PersonajeBuilder withVelocidad() throws CantidadMaximaDeIntentosException {
        int velocidad;
        String velocidadString;
        boolean velocidadInvalida;
        int contador = 0;

        do {
            System.out.print("Ingrese la velocidad de su personaje: ");
            velocidadString = sc.scannerString();
            velocidad = sc.scannerInt(velocidadString);
            if (velocidad < 1 || velocidad > 10){
                velocidadInvalida = true;
                contador++;
                System.out.println("Ingrese una velocidad entre 1 y 10. Intento ("+contador+"/3)");
            }
            else {
                velocidadInvalida = false;
            }
            if (!velocidadInvalida){
                this.velocidad = velocidad;
                break;
            }
        } while(contador < 3);

        return this;
    }

    public PersonajeBuilder withDestreza() throws CantidadMaximaDeIntentosException {
        int destreza;
        String destrezaString;
        boolean destrezaInvalida;
        int contador = 0;

        do {
            System.out.print("Ingrese la destreza de su personaje: ");
            destrezaString = sc.scannerString();
            destreza = sc.scannerInt(destrezaString);
            if (destreza < 1 || destreza > 5){
                destrezaInvalida = true;
                contador++;
                System.out.println("Ingrese una destreza entre 1 y 5. Intento ("+contador+"/3)");
            }
            else {
                destrezaInvalida = false;
            }
            if (!destrezaInvalida){
                this.destreza = destreza;
                break;
            }
        } while(contador < 3);

        return this;
    }

    public PersonajeBuilder withFuerza() throws CantidadMaximaDeIntentosException {
        int fuerza;
        String fuerzaString;
        boolean fuerzaInvalida;
        int contador = 0;

        do {
            System.out.print("Ingrese la fuerza de su personaje: ");
            fuerzaString = sc.scannerString();
            fuerza = sc.scannerInt(fuerzaString);
            if (fuerza < 1 || fuerza > 10){
                fuerzaInvalida = true;
                contador++;
                System.out.println("Ingrese una fuerza entre 1 y 10. Intento ("+contador+"/3)");
            }
            else {
                fuerzaInvalida = false;
            }
            if (!fuerzaInvalida){
                this.fuerza = fuerza;
                break;
            }
        } while(contador < 3);

        return this;
    }

    public PersonajeBuilder withNivel() throws CantidadMaximaDeIntentosException {
        int nivel;
        String nivelString;
        boolean nivelInvalido;
        int contador = 0;

        do {
            System.out.print("Ingrese el nivel de su personaje: ");
            nivelString = sc.scannerString();
            nivel = sc.scannerInt(nivelString);
            if (nivel < 1 || nivel > 10){
                nivelInvalido = true;
                contador++;
                System.out.println("Ingrese un nivel entre 1 y 10. Intento ("+contador+"/3)");
            }
            else {
                nivelInvalido = false;
            }
            if (!nivelInvalido){
                this.nivel = nivel;
                break;
            }
        } while(contador < 3);

        return this;
    }

    public PersonajeBuilder withArmadura() throws CantidadMaximaDeIntentosException {
        int armadura;
        String armaduraString;
        boolean armaduraInvalida;
        int contador = 0;

        do {
            System.out.print("Ingrese la armadura de su personaje: ");
            armaduraString = sc.scannerString();
            armadura = sc.scannerInt(armaduraString);
            if (armadura < 1 || armadura > 10){
                armaduraInvalida = true;
                contador++;
                System.out.println("Ingrese una armadura entre 1 y 10. Intento ("+contador+"/3)");
            }
            else {
                armaduraInvalida = false;
            }
            if (!armaduraInvalida){
                this.armadura = armadura;
                break;
            }
        } while(contador < 3);

        return this;
    }

    public Personaje build() {
        Personaje personaje;

        personaje = this.raza;
        personaje.setNombre(this.nombre);
        personaje.setApodo(this.apodo);
        personaje.setFechaDeNacimiento(this.fechaDeNacimiento);
        personaje.setEdad(this.edad);
        personaje.setSalud(this.salud);
        personaje.setVelocidad(this.velocidad);
        personaje.setDestreza(this.destreza);
        personaje.setFuerza(this.fuerza);
        personaje.setNivel(this.nivel);
        personaje.setArmadura(this.armadura);

        return personaje;
    }
    private static LocalDate fechaDeNacimientoRandom(){
        int anno, mes, dia;
        LocalDate fecha = LocalDate.now();
        boolean fechaCorrecta;

        do {
            fechaCorrecta = true;
            anno = (LocalDate.now().getYear()-1) - (int) (Math.random() * 300);
            mes = (int) (Math.random() * 12 + 1);
            dia = (int) (Math.random() * 31 + 1);
            try {
                fecha = LocalDate.of(anno, mes, dia);
            } catch (Exception exception) {
                fechaCorrecta = false;
            }
        } while (!fechaCorrecta);
        return fecha;
    }
    private static int edadConFechaDeNacimiento(LocalDate fecha){
        int edad;
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fecha, hoy);
        edad = periodo.getYears();

        return edad;
    }
    public Personaje randomBuild() {
        Faker faker = new Faker();

        int clasePersonaje = (int) (Math.random() * 3);
        Personaje personaje;

        if (clasePersonaje == 0) {
            personaje = new Humano();
        } else if (clasePersonaje == 1) {
            personaje = new Elfo();
        } else {
            personaje = new Ogro();
        }



        personaje.setNombre(faker.name().firstName());
        personaje.setApodo(faker.aquaTeenHungerForce().character());
        //todo ver la fecha de faker
        personaje.setFechaDeNacimiento(fechaDeNacimientoRandom());
        personaje.setEdad(edadConFechaDeNacimiento(personaje.getFechaDeNacimiento()));
        personaje.setSalud((int) (Math.random() * 100) + 1);
        personaje.setVelocidad((int) (Math.random() * 10) + 1);
        personaje.setDestreza((int) (Math.random() * 5) + 1);
        personaje.setFuerza((int) (Math.random() * 10 + 1));
        personaje.setNivel((int) (Math.random() * 10 + 1));
        personaje.setArmadura((int) (Math.random() * 10 + 1));

        return personaje;
    }

}
