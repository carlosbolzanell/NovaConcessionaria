package net.weg.topcar.view;

import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;
import net.weg.topcar.model.exceptions.PermissaoNegadasException;
import net.weg.topcar.model.usuarios.Cliente;
import net.weg.topcar.model.usuarios.Gerente;
import net.weg.topcar.model.usuarios.Vendedor;

public class FormularioUsuarioEdicao extends FormularioUsuario{
    public void editarUsuario(){
        try {
            if(!(usuarioAutenticado.getUsuarioAutenticado() instanceof Gerente)){
                throw new PermissaoNegadasException("O usuario não é um gerente!");
            }
            Long cpf = entradaCPF();
            Cliente cliente = usuarioController.buscarUsuario(cpf);
            if(!(cliente instanceof Gerente)) {
                String nome = entradaNome(cliente.getNome());
                Long idade = entradaIdade(cliente.getIdade());
                Cliente clienteEditado;
                if (cliente instanceof Vendedor vendedor) {
                    Double salario = entradaSalario(vendedor.getSalario());
                    clienteEditado = new Vendedor(nome, cliente.getCpf(), cliente.getSenha(), idade, salario);
                    return;
                }
                else {
                    clienteEditado = new Cliente(nome, cliente.getCpf(), cliente.getSenha(), idade);
                }
                usuarioController.alterar(clienteEditado);
            }
        } catch (ObjetoNaoEncontradoException | PermissaoNegadasException e) {
            saida.escrevaln(e.getMessage());
        }
    }
}
