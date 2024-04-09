package net.weg.topcar.view.componentes;

import net.weg.topcar.view.entrada_saida.*;

public class Componente {
    protected final Entrada<Long> entradaInteiro = new EntradaInteiro();
    protected final Entrada<Double> entradaDouble = new EntradaDecimal();
    protected final Entrada<String> entradaString = new EntradaTexto();
    protected final Saida saida = new Saida();
}
