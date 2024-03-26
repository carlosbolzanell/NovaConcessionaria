package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.SenhaIncorretaException;

public class LoginUsuario {
    private final BancoUsuario bancoUsuario;
    private Cliente cliente;

    public LoginUsuario(
            BancoUsuario bancoUsuario){
        this.bancoUsuario = bancoUsuario;
    }
    public Cliente login(Long cpf,
                         String senha)
            throws ObjetoNaoEncontradoException,
            SenhaIncorretaException {
        this.cliente = this.bancoUsuario
                .buscarUm(cpf);
        validarSenha(senha);
        return cliente;
    }
    private void validarSenha(
            String senha)
        throws SenhaIncorretaException {
        if(!cliente.getSenha()
                .equals(senha)){
            throw new SenhaIncorretaException();
        }
    }
}
