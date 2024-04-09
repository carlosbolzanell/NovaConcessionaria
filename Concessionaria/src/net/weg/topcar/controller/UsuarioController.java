package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoAutomoveis;
import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.*;
import net.weg.topcar.service.UsuarioService;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private UsuarioService usuarioService;

    public Cliente adicionar(Cliente cliente) throws UsuarioExistenteException {
        if (cliente != null) {
            validaCPF(cliente.getCpf());
            return usuarioService.adicionar(cliente);
        }
        throw new RuntimeException("usuario nulo!");
    }

    public Cliente buscarUsuario(Long cpf) throws ObjetoNaoEncontradoException {
        return usuarioService.buscarUm(cpf);
    }
    public void alterar(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        isGerente();
        usuarioService.alterar(clienteEditado);
    }
    public List<Automovel> verMeusAutomoveis(){
        return usuarioService.meusAutomoveis();
    }
    public void validaCPF(Long cpf) throws UsuarioExistenteException{
        if(usuarioService.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }
    private Vendedor isVendedor() throws PermissaoNegadaException {
        if (usuarioLogado instanceof Vendedor vendedor) {
            return vendedor;
        }
        throw new PermissaoNegadaException("o usuário não é um vendedor");
    }

    private void isGerente() throws PermissaoNegadaException {
        if (!(usuarioLogado instanceof IGerente)) {
            throw new PermissaoNegadaException("o usuário não é um gerente");
        }
    }

    public void remover(Long cpf) throws ObjetoNaoEncontradoException {
    }

    public void vender(Long cpf, String codigo) throws FalhaNaCompraException{
    }

    public String buscarPagamento(Long cpf) throws ObjetoNaoEncontradoException, TipoDeUsuarioInvalidoException{
    }
}
