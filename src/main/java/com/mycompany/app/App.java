package com.mycompany.app;

import com.mycompany.app.exceptions.CantidadMaximaDeIntentosException;
import java.io.*;
import com.mycompany.app.managers.*;


public class App{
    private static final String NOMBRE_LOG = "log.txt";
    public static void main(String[] args) throws CantidadMaximaDeIntentosException {
        int op;
        int contador = 0;

        System.out.println("Increible juegaso de rol");
        System.out.println("------------------------");

        try {
            FlujoOutputManager.iniciarRedireccion(NOMBRE_LOG);

            do {
                op = MenuManager.menu(contador);

                if (op == 1){
                    FlujoOutputManager.setOutputGuardaEnLog();
                    CombateManager.combate();

                } else if (op == 2) {
                    LogManager.printLog(NOMBRE_LOG);

                } else if (op == 3) {
                    LogManager.borrarLog(NOMBRE_LOG);
                }
                if (op < 0 || op > 3){
                    contador++;
                }
            } while(op != 0);

            System.out.println("Gracias vuelva prontos");

            //Cierro los flujos.
            FlujoOutputManager.finalizarRedireccion();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}