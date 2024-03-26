package net.weg.topcar.model.automoveis;

public class Moto extends Automovel {
    private String partida;
    private Long cilindradas;

    public Moto(String CODIGO, String modelo, Long ano, String marca, String tipoCombustivel, Double preco, Double quilometragem, String placa, String cor, Boolean novo, String partida, Long cilindradas) {
        super(CODIGO, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo);
        this.partida = partida;
        this.cilindradas = cilindradas;
    }

    public String getPartida() {
        return partida;
    }

    public Long getCilindradas() {
        return cilindradas;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nMarcha: " + partida +
                "\nTipo da carroceria: " + cilindradas + " }\n";
    }
}
