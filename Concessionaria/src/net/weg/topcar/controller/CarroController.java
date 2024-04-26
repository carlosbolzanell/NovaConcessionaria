package net.weg.topcar.controller;

import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.automoveis.Carro;
import net.weg.topcar.model.automoveis.Moto;
import net.weg.topcar.model.automoveis.Quadriciclo;
import net.weg.topcar.model.exceptions.AutomovelExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.service.AutomovelService;

public class CarroController extends AutomovelController{
    public CarroController(AutomovelService automovelService, AutomovelService autenticacaoService) {
        super(automovelService, autenticacaoService);
    }

    public void cadastroCarro(String codigo, String marca, String modelo, Long ano, String tipoCombustivel, Double preco, String cor, Boolean novo, Double quilometragem, String placa, String carroceria, String marcha) throws AutomovelExistenteException {
        isGerente();
        Carro automovelNovo = new Carro(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, marcha, carroceria);
        cadastroAutomovel(automovelNovo);
    }
    public void edicaoCarro(String codigo, String marca, String modelo, Long ano, String tipoCombustivel, Double preco, String cor, Boolean novo, Double quilometragem, String placa, String carroceria, String marcha) throws ObjetoNaoEncontradoException {
        isGerente();
        buscarAutomovel(codigo);
        Carro automovelEditado = new Carro(codigo, modelo, ano, marca, tipoCombustivel, preco, quilometragem, placa, cor, novo, marcha, carroceria);
        edicaoAutomovel(automovelEditado);
    }
}
