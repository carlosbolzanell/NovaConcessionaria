package net.weg.topcar.controller;

import net.weg.topcar.model.automoveis.Quadriciclo;
import net.weg.topcar.model.exceptions.AutomovelExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.service.AutomovelService;

public class QuadricicloController extends AutomovelController{

    public QuadricicloController(AutomovelService automovelService, AutomovelService autenticacaoService) {
        super(automovelService, autenticacaoService);
    }

    public void cadastroQuadriciclo(String codigo, String marca, String modelo, Long ano, String tipoCombustivel, Double preco, String cor, Boolean novo, Double quilometragem, String placa) throws AutomovelExistenteException {
        isGerente();
        Quadriciclo automovelNovo = new Quadriciclo(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo);
        cadastroAutomovel(automovelNovo);
    }
    public void edicaoQuadriciclo(String codigo, String marca, String modelo, Long ano, String tipoCombustivel, Double preco, String cor, Boolean novo, Double quilometragem, String placa) throws ObjetoNaoEncontradoException {
        isGerente();
        buscarAutomovel(codigo);
        Quadriciclo automovelEditado = new Quadriciclo(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo);
        edicaoAutomovel(automovelEditado);
    }
}

