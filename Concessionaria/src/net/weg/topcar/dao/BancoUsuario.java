package net.weg.topcar.dao;

import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.Collections;
import java.util.List;

public class BancoUsuario implements
        IBanco<Cliente, String> {
    private List<Cliente> listaClientes;

    public List<Cliente> buscarTodos() {
        return Collections.unmodifiableList(
                listaClientes);
    }

    public Cliente buscarUm(String cpf)
            throws ObjetoNaoEncontradoException {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        throw new ObjetoNaoEncontradoException(cpf);
    }

    public void adicionar(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void remover(String cpf)
            throws ObjetoNaoEncontradoException {
        Cliente cliente = buscarUm(cpf);
        listaClientes.remove(cliente);
    }

    public void alterar(String cpf,
                        Cliente novoCliente)
            throws ObjetoNaoEncontradoException {
        Cliente cliente = buscarUm(cpf);
        listaClientes.set(
                listaClientes.indexOf(cliente),
                novoCliente);
    }

}
