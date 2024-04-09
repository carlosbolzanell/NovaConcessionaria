package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.componentes.Componente;
import net.weg.topcar.view.UsuarioAutenticadoFront;

public class PaginaVerMeuPagamento extends Componente {
    private UsuarioController usuarioController;
    public void verPagamento(){
        try {
            Vendedor vendedor = (Vendedor) usuarioController.buscarUsuario(UsuarioAutenticadoFront.getUsuario().getCpf());
            saida.escrevaln(vendedor.verPagamento());
        }catch (ObjetoNaoEncontradoException e){
            saida.escreva(e.getMessage());
        }catch (ClassCastException e){
            saida.escrevaln("Pemissão negada, você não é um vendedor");
        }catch (NullPointerException e){
            saida.escrevaln("Você deve logar no sistema com um usuário vendedor para execultar essa ação");
        }
    }
}
