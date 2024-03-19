package net.weg.topcar.controller;

import net.weg.topcar.view.*;

public class UsuarioController {
    private final Entrada<Double> entradaDouble = new EntradaDecimal();
    private final Entrada<String> entradaString = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Saida saida = new Saida();

    public void cadastroUsuario(){
        String nome = entradaString.leia("Informe o seu nome: ", saida);
        Long cpf = entradaInteiro.leia("Informe o seu cpf: ", saida);
        Long idade = entradaInteiro.leia("Informe a sua idade: ", saida);
        String senha = entradaString.leia("Informe a sua senha: ", saida);
        String confSenha = entradaString.leia("Confirme a sua senha: ", saida);
    }
}
