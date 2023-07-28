package com.mycompany.app.exceptions;

public class CantidadMaximaDeIntentosException extends Exception{
    public CantidadMaximaDeIntentosException(String mensaje){
        super(mensaje);
    }
}
