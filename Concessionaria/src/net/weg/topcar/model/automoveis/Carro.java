package net.weg.topcar.model.automoveis;

import net.weg.topcar.model.Automovel;

public class Carro extends Automovel {
    private String marcha;
    private String tipoCarroceria;

    public Carro(String CODIGO, String modelo, int ano, String marca, String tipoCombustivel, double preco, int quilometragem, String placa, String cor, String estado, String marcha, String tipoCarroceria) {
        super(CODIGO, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, estado);
        this.marcha = marcha;
        this.tipoCarroceria = tipoCarroceria;
    }

    public String getMarcha() {
        return marcha;
    }

    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    @Override
    public String toString() {
        return "Carro {" +
                "\nCódigo: " + this.getCODIGO() +
                "\nModelo: " + this.getModelo() +
                "\nAno: " + this.getAno() +
                "\nMarca: " + this.getMarca() +
                "\nTipo de combústivel: " + this.getTipoCombustivel() +
                "\nPreço: " + this.getPreco() +
                "\nQuilometragem: " + this.getQuilometragem() +
                "\nPlaca: " + this.getPlaca() +
                "\nCor: " + this.getCor() +
                "\nStatus: " + (this.isComprado() ? "Comprado" : "À venda") +
                "\nEstado: " + this.getEstado() +
                "\nMarcha: " + marcha +
                "\nTipo da carroceria: " + tipoCarroceria + " }\n";
    }
}
