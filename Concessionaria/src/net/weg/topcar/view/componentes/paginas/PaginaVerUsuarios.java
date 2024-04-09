package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.view.componentes.Componente;

import java.util.List;

public class PaginaVerUsuarios extends Componente {
    public void verClientes(){
        try {
            isGerente();
            List<Cliente> listaClientes = bancoUsuario.buscarTodos();
            saida.escreva(listaClientes.toString());
        }catch (PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
}
