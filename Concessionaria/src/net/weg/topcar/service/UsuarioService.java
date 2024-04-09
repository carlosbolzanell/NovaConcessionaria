package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.UsuarioAutenticadoBack;
import net.weg.topcar.model.usuarios.Vendedor;

import java.util.ArrayList;
import java.util.List;

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
    public Cliente buscarUm(Long cpf) throws  ObjetoNaoEncontradoException{
        return usuarioBanco.buscarUm(cpf);
    }
    public List<Automovel> meusAutomoveis(){
        return UsuarioAutenticadoBack.getUsuarioAutenticado().verMeusAutomoveis();
    }


    private void atualizarElvolvidosNaVenda(Cliente cliente, Vendedor vendedor, Automovel automovel) throws ObjetoNaoEncontradoException {
        bancoUsuario.alterar(cliente.getCpf(), cliente);
        bancoUsuario.alterar(vendedor.getCpf(), vendedor);
        bancoAutomovel.alterar(automovel.getCODIGO(), automovel);
    }
    private List<Vendedor> filtrarVendedores(List<Cliente> listaClientes){
        List<Vendedor> listaVendedores = new ArrayList<>();
        listaClientes.forEach(cliente -> {
            if(cliente instanceof Vendedor vendedor){
                listaVendedores.add(vendedor);
            }
        });
        return listaVendedores;
    }
    private List<Vendedor> buscarVendedores(){
        List<Cliente> listaClientes = bancoUsuario.buscarTodos();
        return filtrarVendedores(listaClientes);
    }
    private Automovel buscarAutomovel() throws ObjetoNaoEncontradoException {
        String codigo = entradaCodigo();
        return bancoAutomovel.buscarUm(codigo);
    }
    private void cadastroVendedor(String nome, Long cpf, String senha, Long idade, Double salario){
        bancoUsuario.adicionar(new Vendedor(nome, cpf, senha, idade, salario));
    }
    private void cadastroCliente(String nome, Long cpf, String senha, Long idade){
        bancoUsuario.adicionar(new Cliente(nome, cpf, senha, idade));
    }
}
