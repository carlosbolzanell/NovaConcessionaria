package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;

public class FormularioUsuarioRemover extends FormularioUsuario{
    public void removerUsuario(){
        try {
            isGerente();
            Long cpf = entradaCPF();
            usuarioController.remover(cpf);
        }catch (ObjetoNaoEncontradoException | PermissaoNegadasException e){
            saida.escrevaln(e.getMessage());
        }
    }
}
