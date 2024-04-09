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

    public static Cliente usuarioLogado = null;
    private IBanco<Cliente, Long> bancoUsuario;
    private IBanco<Automovel, String> bancoAutomovel;
    private final Autenticacao autenticacao;
    private UsuarioService usuarioService;

    public UsuarioController(BancoUsuario bancoUsuario, BancoAutomoveis bancoAutomoveis){
        this.bancoUsuario = bancoUsuario;
        this.bancoAutomovel = bancoAutomoveis;
        this.autenticacao = new Autenticacao(bancoUsuario);
    }
    public Cliente adicionar(Cliente cliente) throws UsuarioExistenteException {
        if (cliente != null) {
            validaCPF(cliente.getCpf());
            return usuarioService.adicionar(cliente);
        }
        throw new RuntimeException("usuario nulo!");
    }
    public List<Automovel> verMeusAutomoveis(){
        return usuarioLogado.verMeusAutomoveis();
    }
    //VENDEDOR
    public void verPagamento(){
        try {
            IVendedor vendedor = isVendedor();
            saida.escrevaln(vendedor.verPagamento());
        }catch (PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    //VENDEDOR
    public void vender(){
        try {
            Vendedor vendedor = isVendedor();
            saida.escrevaln("Identifique o comprador");
            Cliente cliente = buscarUsuario();
            saida.escreva("Identifique o automóvel");
            String codigo = entradaString.leiaComSaidaEValidacao("Código: ", saida);
            Automovel automovel = bancoAutomovel.buscarUm(codigo);
            vendedor.vender(automovel, cliente);
            atualizarElvolvidosNaVenda(cliente, vendedor, automovel);
        } catch (ObjetoNaoEncontradoException | FalhaNaCompraException e) {
            saida.escreva(e.getMessage());
        }
    }
    //VENDEDOR
    public void verUsuario(){
        try {
            isVendedor();
            Cliente cliente = buscarUsuario();
            saida.escreva(cliente.toString());
        } catch (ObjetoNaoEncontradoException | PermissaoNegadasException e) {
            saida.escreva(e.getMessage());
        }
    }
    //GERENTE
    public void verClientes(){
        try {
            isGerente();
            List<Cliente> listaClientes = bancoUsuario.buscarTodos();
            saida.escreva(listaClientes.toString());
        }catch (PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    //GERENTE
    public void verVendedores(){
        try {
            isGerente();
            saida.escreva(buscarVendedores().toString());
        }catch (PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }

    }

    //GERENTE
    public void removerUsuario(){
        try {
            isGerente();
            Long cpf = entradaCPF();
            bancoUsuario.remover(cpf);
        }catch (ObjetoNaoEncontradoException | PermissaoNegadasException e){
            saida.escrevaln(e.getMessage());
        }
    }
    //GERENTE
    public void verPagamentoVendedores(){
        try{
            isGerente();
            buscarVendedores().forEach(vendedor -> saida.escreva(vendedor.verPagamento()));
        }catch (PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    public Cliente login () throws ObjetoNaoEncontradoException {
        Long cpf = entradaCPF();
        String senha = entradaString.leiaComSaidaEValidacao("Senha: ", saida);

        usuarioLogado = autenticacao.login(cpf, senha);
        return usuarioLogado;
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
    public Cliente buscarUsuario(Long cpf) throws ObjetoNaoEncontradoException {
        return bancoUsuario.buscarUm(cpf);
    }
    private Automovel buscarAutomovel() throws ObjetoNaoEncontradoException {
        String codigo = entradaCodigo();
        return bancoAutomovel.buscarUm(codigo);
    }
    public void validaCPF(Long cpf) throws UsuarioExistenteException{
        if(usuarioService.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }
    private void cadastroVendedor(String nome, Long cpf, String senha, Long idade, Double salario){
        bancoUsuario.adicionar(new Vendedor(nome, cpf, senha, idade, salario));
    }
    private void cadastroCliente(String nome, Long cpf, String senha, Long idade){
        bancoUsuario.adicionar(new Cliente(nome, cpf, senha, idade));
    }

    public void alterar(Cliente clienteEditado) throws ObjetoNaoEncontradoException {
        isGerente();
        usuarioService.alterar(clienteEditado);
    }
}
