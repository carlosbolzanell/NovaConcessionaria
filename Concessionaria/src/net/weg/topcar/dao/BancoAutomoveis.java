package net.weg.topcar.dao;

import net.weg.topcar.model.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.Collections;
import java.util.List;

public class BancoAutomoveis
    implements IBanco<Automovel, String> {
    private List<Automovel> listaAutomoveis;

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

    public void alterar(String cpf,
                        Automovel novoUsuario)
            throws ObjetoNaoEncontradoException {
        Automovel automovel = buscarUm(cpf);
        listaAutomoveis.set(
                listaAutomoveis.indexOf(automovel),
                novoUsuario);
    }

}
