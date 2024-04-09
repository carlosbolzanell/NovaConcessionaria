package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.UsuarioAutenticadoFront;
import net.weg.topcar.view.componentes.formularios.Formulario;

public class FormularioUsuario extends Formulario {
    protected UsuarioController usuarioController;
    protected String entradaNome(String nome) {
        String novoNome = entradaString.leiaComSaida("Nome: ", saida);
        if(novoNome.isEmpty()){
            return nome;
        }
        return novoNome;
    }
    protected Long entradaIdade(Long idade){
        Long novaIdade = entradaInteiro.leiaComSaida("Idade: ", saida);
        if(novaIdade <= idade){
            return idade;
        }
        return novaIdade;
    }
    Double entradaSalario(Double salario){
        Double novoSalario = entradaDouble.leiaComSaida("Salário: ", saida);
        if(novoSalario <= 0.0){
            return salario;
        }
        return novoSalario;
    }
    protected String entradaCodigo(){
        return entradaString.leiaComSaidaEValidacao("Código: ", saida);
    }
    protected Long entradaCPF(){
        return entradaInteiro.leiaComSaidaEValidacao("Informe o seu cpf: ", saida);
    }
    protected String entradaNome(){
        return entradaString.leiaComSaidaEValidacao("Informe o seu nome: ", saida);
    }
    protected Long entradaIdade(){
        return entradaInteiro.leiaComSaidaEValidacao("Informe a sua idade: ", saida);
    }
    protected String entradaSenha(){
        String senha, confSenha;
        do {
            senha = entradaString.leiaComSaidaEValidacao("Informe a sua senha: ", saida);
            confSenha = entradaString.leiaComSaidaEValidacao("Confirme a sua senha: ", saida);
        } while (!senha.equals(confSenha));
        return senha;
    }
    protected Double entradaSalario(){
        return entradaDouble.leiaComSaidaEValidacao("Salário: ", saida);
    }
    protected Long selecionaTipoDeUsuario(){
        Long entrada;
        do{
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                        Qual o perfil de usuário você deseja cadastrar?
                        1- Vendedor
                        2- Cliente""", saida);
        }while (entrada<2);
        return entrada;
    }

}
