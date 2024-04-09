package net.weg.topcar.view;

import net.weg.topcar.controller.UsuarioController;
import net.weg.topcar.model.exceptions.UsuarioExistenteException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.IGerente;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuarioCadastro extends FormularioUsuario{
    public void cadastroUsuario() {
        try {
            Long cpf = entradaCPF();
            usuarioController.validaCPF(cpf);
            String nome = entradaNome();
            Long idade = entradaIdade();
            String senha = entradaSenha();
            Cliente clienteNovo = null;
            if (usuarioAutenticado.getUsuarioAutenticado() instanceof IGerente) {
                Long escolha = selecionaTipoDeUsuario();
                if (escolha == 1) {
                    Double salario = entradaSalario();
                    clienteNovo = new Vendedor(nome, cpf, senha, idade, salario);
                }
            }
            if(clienteNovo == null){
                clienteNovo = new Cliente(nome, cpf, senha, idade);
            }
            usuarioController.adicionar(clienteNovo);
        }catch(UsuarioExistenteException e){
            saida.escrevaln(e.getMessage());
        }
    }
}
