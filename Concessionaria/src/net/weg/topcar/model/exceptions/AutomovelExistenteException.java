package net.weg.topcar.model.exceptions;

public class AutomovelExistenteException extends Exception{
    public AutomovelExistenteException(String codigo){
        super("Já existe um automóvel com o código: "+ codigo);
    }
}
