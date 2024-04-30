package net.weg.topcar.controller;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.UsuarioExistenteException;
import net.weg.topcar.model.usuarios.Vendedor;
import net.weg.topcar.service.UsuarioService;

public class VendedorController extends UsuarioController{
    public VendedorController(UsuarioService usuarioService) {
        super(usuarioService);
    }
    public void cadastroVendedor(String nome, Long cpf, String senha, Long idade, Double salario) throws UsuarioExistenteException {
        isGerente();
        Vendedor vendedor = new Vendedor(nome, cpf, senha, idade, salario);
        cadastroUsuario(vendedor);
    }
    //adicionar a validação se não esta tenatando editar outro gerente
    public void editarVendedor(String nome, Long cpf, String senha, Long idade, Double salario) throws ObjetoNaoEncontradoException {
        isGerente();
        Vendedor vendedor = new Vendedor(nome, cpf, senha, idade, salario);
        editarUsuario(vendedor);
    }
}
