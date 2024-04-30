package net.weg.topcar.controller;

import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.*;
import net.weg.topcar.service.UsuarioService;

import java.util.List;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    protected Cliente cadastroUsuario(Cliente cliente) throws UsuarioExistenteException {
        if (cliente != null) {
            validaCPF(cliente.getCpf());
            return usuarioService.adicionar(cliente);
        }
        throw new RuntimeException("usuario nulo!");
    }
    protected void editarUsuario(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        usuarioService.alterar(clienteEditado.getCpf(), clienteEditado);
    }

    public Cliente buscarUsuario(Long cpf) throws ObjetoNaoEncontradoException {
        return usuarioService.buscarUm(cpf);
    }
    public List<Automovel> verMeusAutomoveis(){
        return usuarioService.meusAutomoveis();
    }
    public void validaCPF(Long cpf) throws UsuarioExistenteException{
        if(usuarioService.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }
    protected Vendedor isVendedor() throws PermissaoNegadasException {
        if (UsuarioAutenticadoBack.getUsuarioAutenticado() instanceof Vendedor vendedor) {
            return vendedor;
        }
        throw new PermissaoNegadasException("o usuário não é um vendedor");
    }

    protected void isGerente() throws PermissaoNegadasException {
        if (!(UsuarioAutenticadoBack.getUsuarioAutenticado() instanceof IGerente)) {
            throw new PermissaoNegadasException("o usuário não é um gerente");
        }
    }

    public void remover(Long cpf) throws ObjetoNaoEncontradoException {
        isGerente();
        usuarioService.remover(cpf);
    }

    public void vender(Long cpfCliente, String codigo) throws FalhaNaCompraException, ObjetoNaoEncontradoException {
        isVendedor();
        usuarioService.vender(cpfCliente, codigo);
    }
    public List<Vendedor> buscarVendedores(){
        isGerente();
        return usuarioService.buscarVendedores();
    }

    public List<Cliente> buscarUsuarios(){
        isGerente();
        return usuarioService.buscarUsuarios();
    }

    public List<String> buscarPagamentoVendedores(){
        isGerente();
        return usuarioService.buscarPagamentoVendedores();
    }

    public String buscarPagamento(Long cpf) throws ObjetoNaoEncontradoException, TipoDeUsuarioInvalidoException{
        isGerente();
        return usuarioService.buscarPagamento(cpf);
    }
    public String buscarPagamento() throws ObjetoNaoEncontradoException, TipoDeUsuarioInvalidoException{
        isVendedor();
        Vendedor vendedor = (Vendedor)UsuarioAutenticadoBack.getUsuarioAutenticado();
        return usuarioService.buscarPagamento(vendedor.getCpf());

    }
}
