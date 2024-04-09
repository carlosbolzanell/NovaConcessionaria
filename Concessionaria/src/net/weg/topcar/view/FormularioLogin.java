package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;

public class FormularioLogin extends FormularioUsuario{
    public Cliente login () throws ObjetoNaoEncontradoException {
        Long cpf = entradaCPF();
        String senha = entradaString.leiaComSaidaEValidacao("Senha: ", saida);

        usuarioLogado = autenticacao.login(cpf, senha);
        return usuarioLogado;
    }
}
