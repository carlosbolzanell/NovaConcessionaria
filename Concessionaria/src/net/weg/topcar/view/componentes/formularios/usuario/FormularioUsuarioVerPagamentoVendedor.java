package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.exceptions.TipoDeUsuarioInvalidoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IVendedor;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuarioVerPagamentoVendedor extends FormularioUsuario{
    public void verPagamentoVendedor(){
        try {
            isGerente();
            Long cpf = entradaCPF();
            String pagamentoVendedor = usuarioController.buscarPagamento(cpf);
            saida.escreva(pagamentoVendedor);

        } catch (ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException | PermissaoNegadasException e) {
            saida.escrevaln(e.getMessage());
        }
    }
}
