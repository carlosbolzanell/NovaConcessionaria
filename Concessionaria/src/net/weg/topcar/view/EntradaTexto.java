package net.weg.topcar.view;
public class EntradaTexto extends Entrada<String> {
    @Override
    protected boolean validaEntrada(String valor) {
        return !valor.isEmpty();
    }

    public String leia() {
        return  sc.next();
    }

}
