package net.weg.topcar.controller;

import net.weg.topcar.dao.BancoAutomoveis;
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
    private final IBanco<Automovel, String> bancoAutomovel;
    private final Entrada<Double> entradaDouble = new EntradaDecimal();
    private final Entrada<String> entradaString = new EntradaTexto();
    private final Entrada<Long> entradaInteiro = new EntradaInteiro();
    private final Saida saida = new Saida();

    public AutomovelController(BancoAutomoveis bancoAutomoveis){
        this.bancoAutomovel = bancoAutomoveis;
    }
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
            Boolean novo = entradaNovo();
            Double quilometragem = 0.0;
            String placa = "";
            if(!novo){
                quilometragem = entradaQuilometragem();
                placa = entradaPlaca();
            }
            String cor = entradaCor();
            Long tipo = selecionaTipoAutomovel();
            Automovel automovelNovo;
            if (tipo == 1){
                String carroceria = entradaCarrocerioa();
                String marcha = entradaMarcha();
                automovelNovo = new Carro(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, marcha, carroceria);
            } else if (tipo == 2) {
                Long cilindradas = entradaCilindradas();
                String partida = entradaPartida();
                automovelNovo = new Moto(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, partida, cilindradas);
            }else{
                automovelNovo = new Quadriciclo(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo);
            }
            bancoAutomovel.adicionar(automovelNovo);
        }catch (AutomovelExistenteException | PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    public void editarAutomovel(){
        try{
            isGerente();
            Automovel automovel = buscarAutomovel();
            String modelo = entradaModelo(automovel.getModelo());
            Long ano = entradaAno(automovel.getAno());
            String marca = entradaMarca(automovel.getMarca());
            String tipoCombustivel = entradaCombustivel(automovel.getTipoCombustivel());
            Double preco = entradaPreco(automovel.getPreco());
            String cor = entradaCor(automovel.getCor());
            Boolean novo = entradaNovo();
            Double quilometragem = 0.0;
            String placa = "";
            if(!novo){
                quilometragem = entradaQuilometragem(automovel.getQuilometragem());
                placa = entradaPlaca(automovel.getPlaca());
            }
            String codigo = automovel.getCODIGO();
            Automovel automovelEditado;
            if (automovel instanceof Carro carro){
                String carroceria = entradaCarrocerioa(carro.getTipoCarroceria());
                String marcha = entradaMarcha(carro.getMarcha());
                automovelEditado = new Carro(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, marcha, carroceria);
            } else if (automovel instanceof Moto moto) {
                Long cilindradas = entradaCilindradas(moto.getCilindradas());
                String partida = entradaPartida(moto.getPartida());
                automovelEditado = new Moto(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, partida, cilindradas);
            }else{
                automovelEditado = new Quadriciclo(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo);
            }
            bancoAutomovel.alterar(codigo, automovelEditado);
        }catch (ObjetoNaoEncontradoException | PermissaoNegadasException e){
            saida.escreva(e.getMessage());
        }
    }
    private Automovel buscarAutomovel() throws ObjetoNaoEncontradoException{
        String codigo = entradaCodigo();
        return bancoAutomovel.buscarUm(codigo);
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
    private String entradaMarcha(String marcha) {
        String novaMarcha = entradaString.leiaComSaida("Marcha: ", saida);
        if(novaMarcha.isEmpty()){
            return marcha;
        }
        return novaMarcha;
    }
    private String entradaPartida(){
        return entradaString.leiaComSaidaEValidacao("Partida: ", saida);
    }
    private String entradaPartida(String partida){
        String novaPartida = entradaString.leiaComSaida("Partida: ", saida);
        if(novaPartida.isEmpty()){
            return partida;
        }
        return novaPartida;
    }
    private String entradaCarrocerioa(){
        return entradaString.leiaComSaidaEValidacao("Carroceria: ", saida);
    }
    private String entradaCarrocerioa(String carroceria){
        String novaCarroceria = entradaString.leiaComSaida("Carroceria: ", saida);
        if(novaCarroceria.isEmpty()){
            return carroceria;
        }
        return novaCarroceria;
    }
    private Long entradaCilindradas() { return entradaInteiro.leiaComSaidaEValidacao("Cilindradas: ", saida);}
    private Long entradaCilindradas(Long cilindradas) {
        Long novaCilindrada = entradaInteiro.leiaComSaida("Cilindradas: ", saida);
        if(novaCilindrada == 0){
            return cilindradas;
        }
        return novaCilindrada;
    }
    private Double entradaPreco() {return entradaDouble.leiaComSaidaEValidacao("Preço: ", saida);}
    private Double entradaQuilometragem() {return entradaDouble.leiaComSaidaEValidacao("Quilometragem: ", saida);}
    private Double entradaQuilometragem(Double quilometragem) {
        Double novaQuilometragem = entradaDouble.leiaComSaida("Quilometragem: ", saida);
        if(novaQuilometragem <= 0){
            return quilometragem;
        }
        return novaQuilometragem;
    }
    private Long entradaAno() { return entradaInteiro.leiaComSaidaEValidacao("Ano: ", saida);}
    private Long entradaAno(Long ano) {
        Long novoAno = entradaInteiro.leiaComSaida("Ano: ", saida);
        if(novoAno == 0){
            return ano;
        }
        return novoAno;
    }
    private String entradaModelo(){
        return entradaString.leiaComSaidaEValidacao("Modelo: ", saida);
    }
    private String entradaModelo(String modelo){
        String novoModelo = entradaString.leiaComSaida("Modelo: ", saida);
        if(novoModelo.isEmpty()){
            return modelo;
        }
        return novoModelo;
    }
    private String entradaMarca(){
        return entradaString.leiaComSaidaEValidacao("Marca: ", saida);
    }
    private String entradaMarca(String marca){
        String novaMarca = entradaString.leiaComSaidaEValidacao("Marca: ", saida);
        if(novaMarca.isEmpty()){
            return marca;
        }
        return novaMarca;
    }
    private String entradaCombustivel(){
        return entradaString.leiaComSaidaEValidacao("Combustível: ", saida);
    }
    private String entradaCombustivel(String combustivel){
        String novoCombustivel = entradaString.leiaComSaida("Combustível: ", saida);
        if(novoCombustivel.isEmpty()){
            return combustivel;
        }
        return novoCombustivel;
    }
    private String entradaPlaca(){
        return entradaString.leiaComSaidaEValidacao("Placa: ", saida);
    }
    private String entradaPlaca(String placa){
        String novaPlaca = entradaString.leiaComSaida("Placa: ", saida);
        if(novaPlaca.isEmpty()){
            return placa;
        }
        return novaPlaca;
    }
    private String entradaCor(){
        return entradaString.leiaComSaidaEValidacao("Cor: ", saida);
    }
    private String entradaCor(String cor){
        String novaCor = entradaString.leiaComSaida("Cor: ", saida);
        if(novaCor.isEmpty()){
            return cor;
        }
        return novaCor;
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
