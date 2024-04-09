package net.weg.topcar.controller;

import net.weg.topcar.model.usuarios.Cliente;

public class AutenticacaoController {
    private AutenticacaoService autenticacaoService;
    public Cliente login(Long cpf, String senha){
        return autenticacaoService.login(cpf, senha);
    }
    public void logout(){
        autenticacaoService.logout();
    }
}
