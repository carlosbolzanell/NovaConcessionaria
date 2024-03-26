package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoAutomoveis;
import net.weg.topcar.dao.BancoUsuario;
import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.automoveis.Carro;
import net.weg.topcar.model.automoveis.Moto;
import net.weg.topcar.model.automoveis.Quadriciclo;
import net.weg.topcar.model.exceptions.AutomovelExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.view.*;

import java.util.ArrayList;
import java.util.List;

public class AutomovelController {
    public static Cliente usuarioLogado = null;
    private IBanco<Cliente, Long> bancoUsuario = new BancoUsuario();
    private IBanco<Automovel, String> bancoAutomovel = new BancoAutomoveis();
    private final Entrada<Double> entradaDouble = new EntradaDecimal();
    private final Entrada<String> entradaString = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Saida saida = new Saida();

    public void verAutomoveis(){
        List<Automovel> automoveisDisponiveis = filtrarAutomoveisDisponiveis();
        automoveisDisponiveis.forEach(automovel -> saida.escrevaln(automovel.toString()));
    }
    public void verAutomovel(){
        try {
            String codigo = entradaCodigo();
            Automovel automovel = bancoAutomovel.buscarUm(codigo);
            saida.escreva(automovel.toString());
        } catch (ObjetoNaoEncontradoException e) {
            saida.escrevaln(e.getMessage());
        }
    }
    public void removerAutomovel(){
        try{
            isGerente();
            String codigo = entradaCodigo();
            bancoAutomovel.remover(codigo);
        }catch (ObjetoNaoEncontradoException | PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    public void alterarPreco(){
        try{
            isGerente();
            String codigo = entradaCodigo();
            Automovel automovel = bancoAutomovel.buscarUm(codigo);
            Double preco = entradaPreco(automovel.getPreco());
            automovel.setPreco(preco);
            bancoAutomovel.alterar(codigo, automovel);

        }catch (ObjetoNaoEncontradoException | PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    private Double entradaPreco(Double precoAntigo){
        Double novoPreco = entradaDouble.leiaComSaida("Novo preço: ", saida);
        if(novoPreco <= 0.0){
            return precoAntigo;
        }
        return novoPreco;
    }
    public void cadastroAutomovel(){
        try{
            isGerente();
            String codigo = entradaCodigo();
            validaCodigo(codigo);
            String modelo = entradaModelo();
            Long ano = entradaAno();
            String marca = entradaMarca();
            String tipoCombustivel = entradaCombustivel();
            Double preco = entradaPreco();
            Double quilometragem = entradaQuilometragem();
            String placa = entradaPlaca();
            String cor = entradaCor();
            Boolean novo = entradaNovo();
            Long tipo = selecionaTipoAutomovel();
            if (tipo == 1){
                String carroceria = entradaCarrocerioa();
                String marcha = entradaMarcha();
                cadastroCarro(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, marcha, carroceria);
            } else if (tipo == 2) {
                Long cilindradas = entradaCilindradas();
                String partida = entradaPartida();
                cadastroMoto(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, partida, cilindradas);
            }else{
                cadastroQuadriciculo(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo);
            }
        }catch (AutomovelExistenteException | PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    private void cadastroCarro(String CODGO, String modelo, Long ano, String marca, String tipoCombustivel, Double preco, Double quilometragem, String placa, String cor, Boolean novo, String marcha, String tipoCarroceria) {
        Carro carro = new Carro(CODGO, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, marcha, tipoCarroceria);
    }
    private void cadastroMoto(String CODGO, String modelo, Long ano, String marca, String tipoCombustivel, Double preco, Double quilometragem, String placa, String cor, Boolean novo, String partida, Long cilindrada) {
        Moto moto = new Moto(CODGO, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, partida, cilindrada);
    }
    private void cadastroQuadriciculo(String CODGO, String modelo, Long ano, String marca, String tipoCombustivel, Double preco, Double quilometragem, String placa, String cor, Boolean novo) {
        Quadriciclo quadriciclo = new Quadriciclo(CODGO, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo);
    }
    private Long selecionaTipoAutomovel(){
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
    private void validaCodigo(String codigo) throws AutomovelExistenteException {
        if(bancoAutomovel.existe(codigo)){
            throw new AutomovelExistenteException(codigo);
        }
    }
    private Boolean entradaNovo(){
        Long entrada;
        do {
            entrada = entradaInteiro.leiaComSaidaEValidacao("""
                    Carro 0km?
                    1 - Sim
                    2 - Não""", saida);
        }while (entrada > 2);
        return entrada == 1;
    }
    private String entradaMarcha(){
        return entradaString.leiaComSaidaEValidacao("Marcha: ", saida);
    }
    private String entradaPartida(){
        return entradaString.leiaComSaidaEValidacao("Partida: ", saida);
    }
    private String entradaCarrocerioa(){
        return entradaString.leiaComSaidaEValidacao("Carroceria: ", saida);
    }
    private Long entradaCilindradas() { return entradaInteiro.leiaComSaidaEValidacao("Cilindradas: ", saida);}
    private Double entradaPreco() {return entradaDouble.leiaComSaidaEValidacao("Preço: ", saida);}
    private Double entradaQuilometragem() {return entradaDouble.leiaComSaidaEValidacao("Quilometragem: ", saida);}
    private Long entradaAno() { return entradaInteiro.leiaComSaidaEValidacao("Modelo: ", saida);}
    private String entradaModelo(){
        return entradaString.leiaComSaidaEValidacao("Modelo: ", saida);
    }
    private String entradaMarca(){
        return entradaString.leiaComSaidaEValidacao("Marca: ", saida);
    }
    private String entradaCombustivel(){
        return entradaString.leiaComSaidaEValidacao("Combustível: ", saida);
    }
    private String entradaPlaca(){
        return entradaString.leiaComSaidaEValidacao("Placa: ", saida);
    }
    private String entradaCor(){
        return entradaString.leiaComSaidaEValidacao("Cor: ", saida);
    }
    private void isGerente(){
        if(!(usuarioLogado instanceof IGerente)){
            throw new PermissaoNegadasException("Usuário não é um gerente");
        }
    }
    private String entradaCodigo(){
        return entradaString.leiaComSaidaEValidacao("Codigo: ", saida);
    }
    private List<Automovel> filtrarAutomoveisDisponiveis(){
        List<Automovel> listaAutomoveis = bancoAutomovel.buscarTodos();
        List<Automovel> listaAutomoveisDisponiveis = new ArrayList<>();
        listaAutomoveis.forEach(automovel -> {
            if(!automovel.isComprado()){
                listaAutomoveisDisponiveis.add(automovel);
            }
        });
        return listaAutomoveisDisponiveis;
    }
}
