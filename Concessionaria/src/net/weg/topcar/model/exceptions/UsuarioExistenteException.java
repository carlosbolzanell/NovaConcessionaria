package net.weg.topcar.model.exceptions;

public class UsuarioExistenteException extends Exception{
    public UsuarioExistenteException(Long cpf){
        super("Já existe um usuário com o CPF "+cpf+"!");
    }
}
