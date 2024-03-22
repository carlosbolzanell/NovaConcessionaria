package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoAutomoveis;
import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.*;

public class UsuarioController {

    public static Cliente usuarioLogado = null;
    private IBanco<Cliente, Long> bancoUsuario = new BancoUsuario();
    private IBanco<Automovel, String> bancoAutomovel = new BancoAutomoveis();
    private final Entrada<Double> entradaDouble = new EntradaDecimal();
    private final Entrada<String> entradaString = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Saida saida = new Saida();

    public void cadastroUsuario(){
        Cliente cliente;
        String nome = entradaString.leiaComValidacaoEValidacao("Informe o seu nome: ", saida);
        Long cpf = entradaInteiro.leiaComValidacaoEValidacao("Informe o seu cpf: ", saida);
        Long idade = entradaInteiro.leiaComValidacaoEValidacao("Informe a sua idade: ", saida);
        String senha = entradaString.leiaComValidacaoEValidacao("Informe a sua senha: ", saida);
        String confSenha = entradaString.leiaComValidacaoEValidacao("Confirme a sua senha: ", saida);
        if(usuarioLogado == null || !(usuarioLogado instanceof Gerente)){
             cliente = new Cliente(nome, cpf, senha, idade);
        }else {
            Long escolha = entradaInteiro.leiaComValidacaoEValidacao("""
                    Qual o perfil de usuário você deseja cadastrar?
                    1- Vendedor
                    2- Cliente""", saida);
            if(escolha == 1){
                Double salario = entradaDouble.leiaComValidacaoEValidacao("Salário: ", saida);
                cliente = new Vendedor(nome, cpf, senha, idade, salario);
            }
        }
    }
}
