package net.weg.topcar.model.exceptions;

public class FalhaNaCompraException extends Exception{
    public FalhaNaCompraException(String mensagem){
        super("Falha na compra: "+mensagem);
    }
}
