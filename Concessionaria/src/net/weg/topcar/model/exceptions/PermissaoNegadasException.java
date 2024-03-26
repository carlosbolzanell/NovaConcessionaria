package net.weg.topcar.model.exceptions;

public class PermissaoNegadasException extends RuntimeException{
    public PermissaoNegadasException(String mensagem){
        super("Permissão negada: "+mensagem);

    }
}
