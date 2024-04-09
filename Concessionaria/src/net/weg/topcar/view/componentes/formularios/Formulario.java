package net.weg.topcar.view.componentes.formularios;

import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.view.componentes.Componente;
import net.weg.topcar.view.UsuarioAutenticadoFront;

public class Formulario extends Componente {

    protected void isGerente(){
        if(!(UsuarioAutenticadoFront.getUsuario() instanceof IGerente)){
            throw new PermissaoNegadasException("Usuário não é um gerente");
        }
    }
    protected Vendedor isVendedor() {
        if (UsuarioAutenticadoFront.getUsuario() instanceof Vendedor vendedor) {
            return vendedor;
        }
        throw new PermissaoNegadasException("Usuário não é um vendedor");
    }
}
