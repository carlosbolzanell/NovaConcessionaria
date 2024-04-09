package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.view.componentes.Componente;

public class PaginaVerPagamentoVendedores extends Componente {
    public void verPagamentoVendedores(){
        try{
            isGerente();
            buscarVendedores().forEach(vendedor -> saida.escreva(vendedor.verPagamento()));
        }catch (PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
}
