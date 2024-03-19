package net.weg.topcar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Automovel {
    private static ArrayList<Automovel> listaAutomoveis = new ArrayList<Automovel>();
    private final String CODIGO;
    private String modelo;
    private int ano;
    private String marca;
    private String tipoCombustivel;
    private double preco;
    private int quilometragem;
    private String placa;
    private String cor;
    private boolean comprado;
    private String estado;

    public Automovel(String CODIGO, String modelo, int ano, String marca, String tipoCombustivel, double preco, int quilometragem, String placa, String cor, String estado) {
        this.CODIGO = CODIGO;
        this.modelo = modelo;
        this.ano = ano;
        this.marca = marca;
        this.tipoCombustivel = tipoCombustivel;
        this.preco = preco;
        this.quilometragem = quilometragem;
        this.placa = placa;
        this.cor = cor;
        this.estado = estado;
        this.comprado = false;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void mudarStatus() {
        this.comprado = true;
    }

    public static List<Automovel> getListaAutomoveis() {
        return Collections.unmodifiableList(listaAutomoveis);
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public boolean isComprado() {
        return comprado;
    }

    public String getEstado() {
        return estado;
    }

    public static Automovel procurarAutomovel(String codigo ) {
        for (Automovel automovel : listaAutomoveis) {
            if (automovel.CODIGO.equals(codigo)) {
                return automovel;
            }
        }

        return null;
    }

    public void adicionarAutomovel() {
        listaAutomoveis.add(this);
    }

    public void removerAutomovel() {
        listaAutomoveis.remove(this);
    }

    public void editarAutomovel(Automovel novoAutomovel) {
        listaAutomoveis.set(
                listaAutomoveis.indexOf(this),
                novoAutomovel);
    }
}
