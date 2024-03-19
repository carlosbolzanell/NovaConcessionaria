package net.weg.topcar.model.automoveis;

import net.weg.topcar.model.Automovel;

public class Quadriciclo extends Automovel {
    public Quadriciclo(String CODIGO, String modelo, int ano, String marca, String tipoCombustivel, double preco, int quilometragem, String placa, String cor, String estado) {
        super(CODIGO, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, estado);
    }

    @Override
    public String toString() {
        return "Moto {" +
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
                "\nEstado: " + this.getEstado() + " }\n";
    }
}
