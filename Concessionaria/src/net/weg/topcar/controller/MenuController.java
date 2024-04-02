package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoAutomoveis;
import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.view.Entrada;
import net.weg.topcar.view.EntradaInteiro;
import net.weg.topcar.view.EntradaTexto;
import net.weg.topcar.view.Saida;

public class MenuController {
    private static Cliente usuarioLogado;
    private final BancoUsuario bancoUsuario = new BancoUsuario();
    private final BancoAutomoveis bancoAutomoveis = new BancoAutomoveis();
    private final Entrada<String> entradaString = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Saida saida = new Saida();
    private final UsuarioController usuarioController = new UsuarioController(bancoUsuario, bancoAutomoveis);
    private final AutomovelController automovelController = new AutomovelController(bancoAutomoveis);

    public void menuInicial(){
        do{
            Long escolha;
            saida.escrevaln("""
                    Bem vindo ao sistema!
                    1 - Cadastrar-se;
                    2 - Logar;
                    3 - Ver automóveis;
                    4 - Ver automóvel específico;
                    0 - Sair.
                    """);
            do {
                escolha = entradaInteiro.leia();
                acaoMenuInicial(Integer.parseInt(escolha.toString()));
            } while (escolha < 0 || escolha > 4);
        }while (true);
    }
    private void acaoMenuInicial(Integer escolha){
        switch (escolha) {
            case 0 ->{
                saida.escreva("Obrigado por usar nosso sistema");
                System.exit(0);
            }
            case 1 -> usuarioController.cadastroUsuario();
            case 2 -> login();
            case 3 -> automovelController.verAutomoveis();
            case 4 -> automovelController.verAutomovel();
            default -> saida.escreva("Opção inválida!\nDigite novamente: ");
        }
    }
    private void login(){
        try {
            usuarioLogado = usuarioController.login();
            menuDoUsuario();
        }catch (ObjetoNaoEncontradoException e){
            saida.escrevaln(e.getMessage());
        }
    }
    private void menuDoUsuario(){
        do {
            saida.escrevaln(usuarioLogado.menu());
            saida.escrevaln("0 - Deslogar");
            Integer escolha = Integer.parseInt(entradaInteiro.leia().toString());
            try {
                switch (escolha) {
                    case 0 -> usuarioLogado = null;
                    case 1 -> automovelController.verAutomoveis();
                    case 2 -> automovelController.verAutomovel();
                    case 3 -> usuarioController.verMeusAutomoveis();
                    case 4 -> usuarioController.vender();
                    case 5 -> usuarioController.verUsuario();
                    case 6 -> usuarioController.verPagamento();
                    case 7 -> automovelController.cadastroAutomovel();
                    case 8 -> automovelController.removerAutomovel();
                    case 9 -> automovelController.editarAutomovel();
                    case 10 -> automovelController.alterarPreco();
                    case 11 -> usuarioController.cadastroUsuario();
                    case 12 -> usuarioController.removerUsuario();
                    case 13 -> usuarioController.editarUsuario();
                    case 14 -> usuarioController.verVendedores();
                    case 15 -> usuarioController.verClientes();
                    case 16 -> usuarioController.verPagamentoVendedores();
                    case 17 -> usuarioController.verPagamentoVendedor();
                }
            }catch (PermissaoNegadasException e){
                saida.escrevaln("Opção inválida!");
            }
        } while (usuarioLogado != null);
    }
}
