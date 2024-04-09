package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;

public class Formulario {
    protected final Entrada<Long> entradaInteiro = new EntradaInteiro();
    protected final Entrada<Double> entradaDouble = new EntradaDecimal();
    protected final Entrada<String> entradaString = new EntradaTexto();
    protected final Saida saida = new Saida();

    protected void isGerente(Cliente usuarioLogado){
        if(!(usuarioLogado instanceof IGerente)){
            throw new PermissaoNegadasException("Usuário não é um gerente");
        }
    }

}
