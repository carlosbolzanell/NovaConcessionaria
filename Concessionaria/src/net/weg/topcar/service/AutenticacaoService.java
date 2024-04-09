package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.UsuarioAutenticadoBack;

public class AutenticacaoService {
    private IBanco<Cliente, Long> usuarioBanco;

    public Cliente login(Long cpf, String senha) throws ObjetoNaoEncontradoException {
        Cliente cliente = usuarioBanco.buscarUm(cpf);
        if(cliente.getSenha().equals(senha)){
            UsuarioAutenticadoBack.setUsuarioAutenticado(cliente);
            return cliente;
        }
        throw new RuntimeException("Usuario e/ou senha incorretos");
    }

    public void logout() {
        UsuarioAutenticadoBack.setUsuarioAutenticado(null);
    }
}
