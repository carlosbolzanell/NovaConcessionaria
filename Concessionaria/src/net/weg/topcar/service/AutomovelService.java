package net.weg.topcar.service;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.AutomovelExistenteException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class AutomovelService {
    private IBanco<Automovel, String> automovelBanco;
    public List<Automovel> buscarAutomoveisDisponiveis() {
        List<Automovel> automoveis = automovelBanco.buscarTodos();
        return filtrarAutomoveisDisponiveis(automoveis);
    }
    private List<Automovel> filtrarAutomoveisDisponiveis(List<Automovel> automoveis){
        List<Automovel> listaAutomoveisDisponiveis = new ArrayList<>();
        automoveis.forEach(automovel -> {
            if(!automovel.isComprado()){
                listaAutomoveisDisponiveis.add(automovel);
            }
        });
        return listaAutomoveisDisponiveis;
    }

    public Automovel buscarUm(String codigo) throws ObjetoNaoEncontradoException {
        return automovelBanco.buscarUm(codigo);
    }

    public void remover(String codigo) throws ObjetoNaoEncontradoException {
        automovelBanco.remover(codigo);
    }

    public void alterarPreco(String codigo, Double preco) throws ObjetoNaoEncontradoException {
        Automovel automovel = automovelBanco.buscarUm(codigo);
        automovel.setPreco(preco);
        automovelBanco.alterar(codigo, automovel);
    }

    public void adicionar(Automovel automovelNovo) throws AutomovelExistenteException {
        validaCodigo(automovelNovo.getCODIGO());
        automovelBanco.adicionar(automovelNovo);
    }
    private void validaCodigo(String codigo) throws AutomovelExistenteException {
        if(automovelBanco.existe(codigo)){
            throw new AutomovelExistenteException(codigo);
        }
    }

    public void alterar(String codigo, Automovel automovelEditado) throws ObjetoNaoEncontradoException {
        automovelBanco.alterar(codigo, automovelEditado);
    }
}
