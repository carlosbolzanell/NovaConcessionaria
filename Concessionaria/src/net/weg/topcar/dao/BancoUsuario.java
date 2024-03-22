package net.weg.topcar.dao;

import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.Collections;
import java.util.List;

public class BancoUsuario implements
        IBanco<Cliente, Long> {
    private List<Cliente> listaClientes;

    public List<Cliente> buscarTodos() {
        return Collections.unmodifiableList(
                listaClientes);
    }

    public Cliente buscarUm(Long cpf)
            throws ObjetoNaoEncontradoException {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        throw new ObjetoNaoEncontradoException(cpf.toString());
    }

    public void adicionar(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void remover(Long cpf)
            throws ObjetoNaoEncontradoException {
        Cliente cliente = buscarUm(cpf);
        listaClientes.remove(cliente);
    }

    public void alterar(Long cpf,
                        Cliente novoCliente)
            throws ObjetoNaoEncontradoException {
        Cliente cliente = buscarUm(cpf);
        listaClientes.set(
                listaClientes.indexOf(cliente),
                novoCliente);
    }

}
