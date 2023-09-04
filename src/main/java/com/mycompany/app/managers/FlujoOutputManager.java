package com.mycompany.app.managers;

import org.apache.commons.io.output.TeeOutputStream;

import java.io.*;
public class FlujoOutputManager {
    private static PrintStream originalOut = System.out;
    private static PrintStream logPrintStream;
    private static FileOutputStream fileOutputStream;

    public static void iniciarRedireccion(String archivoLog) throws FileNotFoundException {
        try {
            //Abre un archivo log.txt para escribir, para eso es el FileOutputStream
            //El true es para que no me cree otro archivo con el mismo nombre y me pise la info guardada.
            fileOutputStream = new FileOutputStream(archivoLog, true);

            //El TeeOutputStream te deja splettear la salida del System.out en el fileOutoutStream
            TeeOutputStream teeOutputStream = new TeeOutputStream(originalOut, fileOutputStream);

            //PrintStream es una clase que te permite imprimir datos en la consola o en otros flujos de salida.
            logPrintStream = new PrintStream(teeOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setOutputGuardaEnLog(){
        System.setOut(logPrintStream);
    }
    public static void setOutputSoloConsola() {
        System.setOut(originalOut);
    }

    public static void finalizarRedireccion() throws IOException {
        logPrintStream.close();
        fileOutputStream.close();
    }

}
