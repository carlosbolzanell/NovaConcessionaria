package net.weg.topcar.view.componentes.formularios.usuario;

import net.weg.topcar.controller.AutomovelController;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.FalhaNaCompraException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuarioVenda extends FormularioUsuario{
    public void vender(){
        try {
            isVendedor();
            saida.escrevaln("Identifique o comprador");
            Long cpf = entradaCPF();
            saida.escreva("Identifique o automóvel");
            String codigo = entradaCodigo();
            usuarioController.vender(cpf, codigo);
        } catch (FalhaNaCompraException e) {
            saida.escreva(e.getMessage());
        }
    }
}
