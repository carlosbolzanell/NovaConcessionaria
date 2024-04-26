package net.weg.topcar.controller;

import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.automoveis.Carro;
import net.weg.topcar.model.automoveis.Moto;
import net.weg.topcar.model.automoveis.Quadriciclo;
import net.weg.topcar.model.exceptions.AutomovelExistenteException;
import net.weg.topcar.model.exceptions.FalhaNaCompraException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.UsuarioAutenticadoBack;
import net.weg.topcar.service.AutomovelService;

import java.util.List;

public class AutomovelController {
    protected final AutomovelService automovelService;
    private final AutomovelService autenticacaoService;
    public AutomovelController(AutomovelService automovelService, AutomovelService autenticacaoService){
        this.automovelService = automovelService;
        this.autenticacaoService = automovelService;
    }
    public List<Automovel> buscarAutomoveisDisponiveis(){
        return automovelService.buscarAutomoveisDisponiveis();
    }
    public Automovel buscarAutomovel(String codigo) throws ObjetoNaoEncontradoException {
        return automovelService.buscarUm(codigo);
    }
    public void removerAutomovel(String codigo) throws ObjetoNaoEncontradoException {
        isGerente();
        automovelService.remover(codigo);
    }
    public void alterarPreco(String codigo, Double preco) throws ObjetoNaoEncontradoException {
        isGerente();
        automovelService.alterarPreco(codigo, preco);
    }
    protected void cadastroAutomovel(Automovel automovel) throws AutomovelExistenteException {
        automovelService.adicionar(automovel);
    }
    protected void edicaoAutomovel (Automovel automovel) throws ObjetoNaoEncontradoException {
        automovelService.alterar(automovel.getCODIGO(), automovel);
    }
    protected void isGerente(){
        if(!(autenticacaoService.getUsuarioLogado() instanceof IGerente)){
            throw new PermissaoNegadasException("Usuário não é um gerente");
        }
    }
}
