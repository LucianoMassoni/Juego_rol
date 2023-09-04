package com.mycompany.app.managers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class LogManager {
    public static void printLog(String nombreLog) {
        File log = new File(nombreLog);


        //Cambio el flujo para que no se guarde en el log
        FlujoOutputManager.setOutputSoloConsola();

        if (log.exists()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(log.toPath()), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("No existe el archivo del log");
        }
        //seteo el flujo para que lo guarde en el log
        FlujoOutputManager.setOutputGuardaEnLog();
    }
    public static void borrarLog(String nombreLog){
        File log = new File(nombreLog);
        if (log.exists()){
            log.delete();
        } else {
            System.out.println("No existe el archivo");
        }
    }
}
