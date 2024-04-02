package net.weg.topcar.dao;

import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BancoAutomoveis
    implements IBanco<Automovel, String> {
    private List<Automovel> listaAutomoveis;

    public BancoAutomoveis(){
        this.listaAutomoveis = new ArrayList<>();
    }

    public List<Automovel> buscarTodos() {
        return Collections.unmodifiableList(
                listaAutomoveis);
    }

    public Automovel buscarUm(String codigo)
            throws ObjetoNaoEncontradoException {
        for (Automovel automovel : listaAutomoveis) {
            if (automovel.getCODIGO().equals(codigo)) {
                return automovel;
            }
        }
        throw new ObjetoNaoEncontradoException(codigo);
    }

    public void adicionar(Automovel automovel) {
        listaAutomoveis.add(automovel);
    }

    public void remover(String codigo)
            throws ObjetoNaoEncontradoException {
        Automovel automovel = buscarUm(codigo);
        listaAutomoveis.remove(automovel);
    }

    @Override
    public Boolean existe(String codigo) {
        try {
            buscarUm(codigo);
            return true;
        } catch (ObjetoNaoEncontradoException e) {
            return false;
        }
    }

    public void alterar(String codigo,
                        Automovel novoAutomovel)
            throws ObjetoNaoEncontradoException {
        Automovel automovel = buscarUm(codigo);
        listaAutomoveis.set(
                listaAutomoveis.indexOf(automovel),
                novoAutomovel);
    }

}
