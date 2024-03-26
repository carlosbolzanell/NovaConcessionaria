package net.weg.topcar.view;

import java.util.InputMismatchException;

public class EntradaDecimal extends Entrada<Double>{
    @Override
    protected boolean validaEntrada(Double valor) {
        return valor > 0.0;
    }

    @Override
    public Double leia() throws InputMismatchException {
        return sc.nextDouble();
    }
}
