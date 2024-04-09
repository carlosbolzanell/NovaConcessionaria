package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.view.componentes.Componente;

public class PaginaVerVendedores extends Componente {
    public void verVendedores(){
        try {
            isGerente();
            saida.escreva(buscarVendedores().toString());
        }catch (PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }

    }
}
