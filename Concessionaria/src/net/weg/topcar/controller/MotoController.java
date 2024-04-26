package net.weg.topcar.controller;

import net.weg.topcar.model.automoveis.Carro;
import net.weg.topcar.model.automoveis.Moto;
import net.weg.topcar.model.exceptions.AutomovelExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.service.AutomovelService;

public class MotoController extends AutomovelController{
    public MotoController(AutomovelService automovelService, AutomovelService autenticacaoService) {
        super(automovelService, autenticacaoService);
    }

    public void cadastroMoto(String codigo, String marca, String modelo, Long ano, String tipoCombustivel, Double preco, String cor, Boolean novo, Double quilometragem, String placa, String partida, Long cilindradas) throws AutomovelExistenteException {
        isGerente();
        Moto automovelNovo = new Moto(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, partida, cilindradas);
        cadastroAutomovel(automovelNovo);
    }
    public void edicaoMoto(String codigo, String marca, String modelo, Long ano, String tipoCombustivel, Double preco, String cor, Boolean novo, Double quilometragem, String placa, String partida, Long cilindradas) throws ObjetoNaoEncontradoException {
        isGerente();
        buscarAutomovel(codigo);
        Moto automovelEditado = new Moto(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, partida, cilindradas);
        edicaoAutomovel(automovelEditado);
    }

}
