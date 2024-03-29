package com.mycompany.app;

import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;
import java.io.*;
import com.mycompany.app.managers.*;


public class App{
    private static final String NOMBRE_LOG = "log.txt";

    public static void main(String[] args){
        int op = 0;
        int contador = 0;

        System.out.println("Increible juegaso de rol");
        System.out.println("------------------------");

        try {
            FlujoOutputManager.iniciarRedireccion(NOMBRE_LOG);

            do {
                try {
                    op = MenuManager.menu(contador);

                    if (op == 1){
                        FlujoOutputManager.setOutputGuardaEnLog();
                        CombateManager.combate();

                    } else if (op == 2) {
                        LogManager.printLog(NOMBRE_LOG);

                    } else if (op == 3) {
                        //Cierro la redireccion para que el archivo se cierre y me deje borrarlo
                        FlujoOutputManager.cerrarRedireccion();
                        LogManager.borrarLog(NOMBRE_LOG);
                        //Vuelvo a abrir el flujo por si luego de borrar el archivo se quiere seguir jugando, y se guarde.
                        FlujoOutputManager.iniciarRedireccion(NOMBRE_LOG);
                    }
                    if (op < 0 || op > 3){
                        contador++;
                    }
                } catch (CantidadMaximaDeIntentosException e){
                    System.out.println("\nError al ingresar reiteradamente algún dato.\nSe supero la cantidad maxima de intentos.\n\n");
                }
            } while(op != 0);

            //Cierro los flujos.
            FlujoOutputManager.finalizarRedireccion();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}