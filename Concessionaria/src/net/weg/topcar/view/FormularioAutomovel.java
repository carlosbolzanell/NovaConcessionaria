package net.weg.topcar.view;

public class FormularioAutomovel extends Formulario {
    protected Long selecionaTipoAutomovel(){
        Long entrada;
        do{
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                Qual o tipo de veiculo 
                1 - Carro
                2 - Moto
                3 - Quadriciculo""", saida);
        }while (entrada> 3);
        return entrada;
    }
    protected Boolean entradaNovo(){
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Carro 0km?
                    1 - Sim
                    2 - Não""", saida);
        }while (entrada > 2);
        return entrada == 1;
    }
    protected String entradaMarcha(){
        return entradaString.leiaComSaidaEValidacao("Marcha: ", saida);
    }
    protected String entradaMarcha(String marcha) {
        String novaMarcha = entradaString.leiaComSaida("Marcha: ", saida);
        if(novaMarcha.isEmpty()){
            return marcha;
        }
        return novaMarcha;
    }
    protected String entradaPartida(){
        return entradaString.leiaComSaidaEValidacao("Partida: ", saida);
    }
    protected String entradaPartida(String partida){
        String novaPartida = entradaString.leiaComSaida("Partida: ", saida);
        if(novaPartida.isEmpty()){
            return partida;
        }
        return novaPartida;
    }
    protected String entradaCarrocerioa(){
        return entradaString.leiaComSaidaEValidacao("Carroceria: ", saida);
    }
    protected String entradaCarrocerioa(String carroceria){
        String novaCarroceria = entradaString.leiaComSaida("Carroceria: ", saida);
        if(novaCarroceria.isEmpty()){
            return carroceria;
        }
        return novaCarroceria;
    }
    protected Long entradaCilindradas() { return entradaInteiro.leiaComSaidaEValidacao("Cilindradas: ", saida);}
    protected Long entradaCilindradas(Long cilindradas) {
        Long novaCilindrada = entradaInteiro.leiaComSaida("Cilindradas: ", saida);
        if(novaCilindrada == 0){
            return cilindradas;
        }
        return novaCilindrada;
    }
    protected Double entradaPreco() {return entradaDouble.leiaComSaidaEValidacao("Preço: ", saida);}
    protected Double entradaQuilometragem() {return entradaDouble.leiaComSaidaEValidacao("Quilometragem: ", saida);}
    protected Double entradaQuilometragem(Double quilometragem) {
        Double novaQuilometragem = entradaDouble.leiaComSaida("Quilometragem: ", saida);
        if(novaQuilometragem <= 0){
            return quilometragem;
        }
        return novaQuilometragem;
    }
    protected Long entradaAno() { return entradaInteiro.leiaComSaidaEValidacao("Ano: ", saida);}
    protected Long entradaAno(Long ano) {
        Long novoAno = entradaInteiro.leiaComSaida("Ano: ", saida);
        if(novoAno == 0){
            return ano;
        }
        return novoAno;
    }
    protected String entradaModelo(){
        return entradaString.leiaComSaidaEValidacao("Modelo: ", saida);
    }
    protected String entradaModelo(String modelo){
        String novoModelo = entradaString.leiaComSaida("Modelo: ", saida);
        if(novoModelo.isEmpty()){
            return modelo;
        }
        return novoModelo;
    }
    protected String entradaMarca(){
        return entradaString.leiaComSaidaEValidacao("Marca: ", saida);
    }
    protected String entradaMarca(String marca){
        String novaMarca = entradaString.leiaComSaidaEValidacao("Marca: ", saida);
        if(novaMarca.isEmpty()){
            return marca;
        }
        return novaMarca;
    }
    protected String entradaCombustivel(){
        return entradaString.leiaComSaidaEValidacao("Combustível: ", saida);
    }
    protected String entradaCombustivel(String combustivel){
        String novoCombustivel = entradaString.leiaComSaida("Combustível: ", saida);
        if(novoCombustivel.isEmpty()){
            return combustivel;
        }
        return novoCombustivel;
    }
    protected String entradaPlaca(){
        return entradaString.leiaComSaidaEValidacao("Placa: ", saida);
    }
    protected String entradaPlaca(String placa){
        String novaPlaca = entradaString.leiaComSaida("Placa: ", saida);
        if(novaPlaca.isEmpty()){
            return placa;
        }
        return novaPlaca;
    }
    protected String entradaCor(){
        return entradaString.leiaComSaidaEValidacao("Cor: ", saida);
    }
    protected String entradaCor(String cor){
        String novaCor = entradaString.leiaComSaida("Cor: ", saida);
        if(novaCor.isEmpty()){
            return cor;
        }
        return novaCor;
    }
    protected String entradaCodigo(){
        return entradaString.leiaComSaidaEValidacao("Codigo: ", saida);
    }

}
