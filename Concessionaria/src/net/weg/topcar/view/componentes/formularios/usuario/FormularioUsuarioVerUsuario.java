package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.view.componentes.Componente;

public class FormularioUsuarioVerUsuario extends FormularioUsuario {
    public void verUsuario(){
        try {
            isVendedor();
            Long cpf = entradaCPF();
            Cliente cliente = usuarioController.buscarUsuario(cpf);
            saida.escreva(cliente.toString());
        } catch (ObjetoNaoEncontradoException | PermissaoNegadasException e) {
            saida.escreva(e.getMessage());
        }
    }
}
