package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.List;

public interface ICliente {
    List<Automovel> verAutomoveis(IBanco<Automovel, String> banco);
    List<Automovel> verMeusAutomoveis();
    Automovel verAutomovel(String codigo, IBanco<Automovel, String> banco) throws ObjetoNaoEncontradoException;
}
