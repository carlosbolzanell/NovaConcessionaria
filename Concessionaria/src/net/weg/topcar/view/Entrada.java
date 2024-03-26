package net.weg.topcar.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Entrada<T>{
    protected final Scanner sc = new Scanner(System.in);

    public T leiaComSaidaEValidacao(String mensagem, Saida saida){
        T valor;
        do{
            valor = leiaComSaida(mensagem,saida);
        }while (!validaEntrada(valor));
        return valor;
    }
    public T leiaComValidacao(){
        T valor;
        do{
            valor = leia();
        }while (!validaEntrada(valor));
        return valor;
    }
    protected abstract boolean validaEntrada( T valor);
    public abstract T leia() throws InputMismatchException;
    public T leiaComSaida(String texto, Saida saida) throws InputMismatchException {
        saida.escreva(texto);
        return leia();
    }
}
