package net.weg.topcar.model.automoveis;

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
        return super.toString() +
                "\nMarcha: " + marcha +
                "\nTipo da carroceria: " + tipoCarroceria + " }\n";
    }
}
