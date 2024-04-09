package net.weg.topcar.view.componentes.formularios;

import net.weg.topcar.controller.AutenticacaoController;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.view.UsuarioAutenticadoFront;
import net.weg.topcar.view.componentes.formularios.usuario.FormularioUsuario;

public class FormularioLogin extends FormularioUsuario {
    protected AutenticacaoController autenticacaoController;
    public void login () {
        Long cpf = entradaCPF();
        String senha = entradaSenha();
        try {
            UsuarioAutenticadoFront.setUsuario(autenticacaoController.login(cpf, senha));
        }catch (ObjetoNaoEncontradoException e){
            saida.escrevaln(e.getMessage());
        }
    }
}
