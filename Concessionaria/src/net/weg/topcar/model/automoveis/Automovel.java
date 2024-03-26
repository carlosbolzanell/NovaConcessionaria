package net.weg.topcar.model.automoveis;

public abstract class Automovel {
    private final String CODIGO;
    private String modelo;
    private Long ano;
    private String marca;
    private String tipoCombustivel;
    private Double preco;
    private Double quilometragem;
    private String placa;
    private String cor;
    private Boolean comprado;
    private Boolean novo;

    public Automovel(String CODIGO, String modelo, Long ano, String marca, String tipoCombustivel, Double preco, Double quilometragem, String placa, String cor, Boolean novo) {
        this.CODIGO = CODIGO;
        this.modelo = modelo;
        this.ano = ano;
        this.marca = marca;
        this.tipoCombustivel = tipoCombustivel;
        this.preco = preco;
        this.quilometragem = quilometragem;
        this.placa = placa;
        this.cor = cor;
        this.novo = novo;
        this.comprado = false;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void mudarStatus() {
        this.comprado = true;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public String getModelo() {
        return modelo;
    }

    public Long getAno() {
        return ano;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public Double getPreco() {
        return preco;
    }

    public Double getQuilometragem() {
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

    public Boolean isNovo() {
        return novo;
    }

    @Override
    public String toString() {
        return "Código: " + this.getCODIGO() +
                "\nModelo: " + this.getModelo() +
                "\nAno: " + this.getAno() +
                "\nMarca: " + this.getMarca() +
                "\nTipo de combústivel: " + this.getTipoCombustivel() +
                "\nPreço: " + this.getPreco() +
                "\nQuilometragem: " + this.getQuilometragem() +
                "\nPlaca: " + this.getPlaca() +
                "\nCor: " + this.getCor() +
                "\nStatus: " + (this.isComprado() ? "Comprado" : "À venda") +
                "\nEstado: " + this.isNovo();
    }
}
