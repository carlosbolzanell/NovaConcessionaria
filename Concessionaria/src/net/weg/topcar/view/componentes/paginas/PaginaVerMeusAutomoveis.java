package net.weg.topcar.view.componentes.paginas;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.view.componentes.Componente;

import java.util.List;

public class PaginaVerMeusAutomoveis extends Componente {
    private UsuarioController usuarioController;

    public void verMeusAutomoveis(){
        List<Automovel> meusAutomoveis = usuarioController.verMeusAutomoveis();
        meusAutomoveis.forEach(automovel -> saida.escrevaln(automovel.toString()));
    }}