package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;

public class UsuarioService {
    private IBanco<Cliente, Long> usuarioBanco;

    public Cliente adicionar(Cliente cliente) {
        return usuarioBanco.adicionar(cliente);
    }

    public boolean existe(Long cpf) {
        return usuarioBanco.existe(cpf);
    }

    public void alterar(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        usuarioBanco.alterar(clienteEditado.getCpf(), clienteEditado);
    }
}
