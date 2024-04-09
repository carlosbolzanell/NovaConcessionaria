package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.exceptions.TipoDeUsuarioInvalidoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IVendedor;

public class FormularioUsuarioVerPagamentoVendedor extends FormularioUsuario{
    public void verPagamentoVendedor(){
        try {
            isGerente();
            Long cpf = entradaCPF();
            Cliente cliente = usuarioController.buscarUsuario(cpf);
            if(cliente instanceof IVendedor vendedor){
                saida.escreva(vendedor.verPagamento());
            }else{
                throw new TipoDeUsuarioInvalidoException(cliente);
            }
        } catch (ObjetoNaoEncontradoException | TipoDeUsuarioInvalidoException | PermissaoNegadasException e) {
            saida.escrevaln(e.getMessage());
        }
    }
}
