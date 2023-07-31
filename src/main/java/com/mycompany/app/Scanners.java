package com.mycompany.app;

import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;

import java.util.Scanner;

public class Scanners {
    Scanner in = new Scanner(System.in);

    public int scannerInt(String numero) throws CantidadMaximaDeIntentosException {
        int numeroInt = 0;
        boolean flag = false;
        int contadorIntentos = 1;
        boolean volverIngresarDato = false;

        do {
            try {
                if (volverIngresarDato && contadorIntentos < 4){
                    System.out.print("Ingrese un numero valido: ");
                    numero = in.next();
                }
                numeroInt = Integer.parseInt(numero);
                flag = true;
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar un numero (" + contadorIntentos +"/3)");
                volverIngresarDato = true;
                contadorIntentos++;
            }
            if (contadorIntentos == 4) {
                throw new CantidadMaximaDeIntentosException("Supero la cantidad maxima de intentos");
            }
        } while (!flag);
        return numeroInt;
    }

    public double scannerDouble(String numero) throws CantidadMaximaDeIntentosException {
        double numeroDouble = 0;
        boolean flag = false;
        int contadorIntentos = 1;
        boolean volverIngresarDato = false;

        do {
            try {
                if (volverIngresarDato && contadorIntentos < 4){
                    System.out.println("Ingrese un numero valido: ");
                    numero = in.next();
                }
                numeroDouble = Double.parseDouble(numero);
                flag = true;
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar un numero (" + contadorIntentos +"/3)");
                volverIngresarDato = true;
                contadorIntentos++;
            }
            if (contadorIntentos == 4) {
                throw new CantidadMaximaDeIntentosException("Supero la cantidad maxima de intentos");
            }
        } while (!flag);
        return numeroDouble;
    }

    public String scannerString(){
        return in.next();
    }
}