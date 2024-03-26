package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoAutomoveis;
import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.*;
import net.weg.topcar.model.usuarios.*;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    public static Cliente usuarioLogado = null;
    private IBanco<Cliente, Long> bancoUsuario = new BancoUsuario();
    private IBanco<Automovel, String> bancoAutomovel = new BancoAutomoveis();
    private final Entrada<Double> entradaDouble = new EntradaDecimal();
    private final Entrada<String> entradaString = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Saida saida = new Saida();

    //CLIENTE
    public void cadastroUsuario() {
        try {
            Long cpf = entradaCPF();
            validaCPF(cpf);
            String nome = entradaNome();
            Long idade = entradaIdade();
            String senha = entradaSenha();
            if (usuarioLogado instanceof IGerente) {
                Long escolha = selecionaTipoDeUsuario();
                if (escolha == 1) {
                    Double salario = entradaSalario();
                    cadastroVendedor(nome, cpf, senha, idade, salario);
                    return;
                }
            }
            cadastroCliente(nome, cpf, senha, idade);
        }catch(UsuarioExistenteException e){
            saida.escrevaln(e.getMessage());
        }
    }
    //CLIENTE
    public void verMeusAutomoveis(){
        List<Automovel> meusAutomoveis = usuarioLogado.verMeusAutomoveis();
        meusAutomoveis.forEach(automovel -> saida.escrevaln(automovel.toString()));
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
            String codigo =entradaString.leiaComSaidaEValidacao("Código: ", saida);
            Automovel automovel = bancoAutomovel.buscarUm(codigo);
            vendedor.vender(automovel, cliente);
            atualizarElvolvidosNaVenda(cliente, vendedor, automovel);
        } catch (ObjetoNaoEncontradoException | FalhaNaCompraException | PermissaoNegadasException e) {
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
    public void editarUsuario(){
        try {
            isGerente();
            Cliente cliente = buscarUsuario();
            if(!(cliente instanceof Gerente)) {
                String nome = entradaNome(cliente.getNome());
                Long idade = entradaIdade(cliente.getIdade());
                if (cliente instanceof Vendedor vendedor) {
                    Double salario = entradaSalario(vendedor.getSalario());
                    bancoUsuario.alterar(vendedor.getCpf(), new Vendedor(nome, cliente.getCpf(), cliente.getSenha(), idade, salario));
                    return;
                }
                bancoUsuario.alterar(cliente.getCpf(), new Cliente(nome, cliente.getCpf(), cliente.getSenha(), idade));
            }
        } catch (ObjetoNaoEncontradoException | PermissaoNegadasException e) {
            saida.escrevaln(e.getMessage());
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
    //GERENTE
    public void verPagamentoVendedor(){
        try {
            isGerente();
            Cliente cliente = buscarUsuario();
            if(cliente instanceof IVendedor vendedor){
                saida.escreva(vendedor.verPagamento());
            }else{
                throw new TipoDeUsuarioInvalidoException(cliente);
            }
        } catch (ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException | PermissaoNegadasException e) {
            saida.escrevaln(e.getMessage());
        }
    }

    private Vendedor isVendedor() {
        if (usuarioLogado instanceof Vendedor vendedor) {
            return vendedor;
        }
        throw new PermissaoNegadasException("Usuário não é um vendedor");
    }
    private void isGerente(){
        if(!(usuarioLogado instanceof IGerente)){
            throw new PermissaoNegadasException("Usuário não é um gerente");
        }
    }
    private String entradaNome(String nome) {
        String novoNome = entradaString.leiaComSaida("Nome: ", saida);
        if(novoNome.isEmpty()){
            return nome;
        }
        return novoNome;
    }
    private Long entradaIdade(Long idade){
        Long novaIdade = entradaInteiro.leiaComSaida("Idade: ", saida);
        if(novaIdade <= idade){
            return idade;
        }
        return novaIdade;
    }
    private  Double entradaSalario(Double salario){
        Double novoSalario = entradaDouble.leiaComSaida("Salário: ", saida);
        if(novoSalario <= 0.0){
            return salario;
        }
        return novoSalario;
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
    private Cliente buscarUsuario() throws ObjetoNaoEncontradoException {
        Long cpf = entradaCPF();
        return bancoUsuario.buscarUm(cpf);
    }
    private String entradaCodigo(){
        return entradaString.leiaComSaidaEValidacao("Código: ", saida);
    }
    private Automovel buscarAutomovel() throws ObjetoNaoEncontradoException {
        String codigo = entradaCodigo();
        return bancoAutomovel.buscarUm(codigo);
    }
    private void validaCPF(Long cpf) throws UsuarioExistenteException{
        if(bancoUsuario.existe(cpf)) {
            throw new UsuarioExistenteException(cpf);
        }
    }
    private Long entradaCPF(){
        return entradaInteiro.leiaComSaidaEValidacao("Informe o seu cpf: ", saida);
    }
    private String entradaNome(){
        return entradaString.leiaComSaidaEValidacao("Informe o seu nome: ", saida);
    }
    private Long entradaIdade(){
        return entradaInteiro.leiaComSaidaEValidacao("Informe a sua idade: ", saida);
    }
    private String entradaSenha(){
        String senha, confSenha;
        do {
            senha = entradaString.leiaComSaidaEValidacao("Informe a sua senha: ", saida);
            confSenha = entradaString.leiaComSaidaEValidacao("Confirme a sua senha: ", saida);
        } while (!senha.equals(confSenha));
        return senha;
    }
    private Double entradaSalario(){
        return entradaDouble.leiaComSaidaEValidacao("Salário: ", saida);
    }
    private Long selecionaTipoDeUsuario(){
        return entradaInteiro.leiaComSaidaEValidacao("""
                        Qual o perfil de usuário você deseja cadastrar?
                        1- Vendedor
                        2- Cliente""", saida);
    }
    private void cadastroVendedor(String nome, Long cpf, String senha, Long idade, Double salario){
        bancoUsuario.adicionar(new Vendedor(nome, cpf, senha, idade, salario));
    }
    private void cadastroCliente(String nome, Long cpf, String senha, Long idade){
        bancoUsuario.adicionar(new Cliente(nome, cpf, senha, idade));
    }
}
