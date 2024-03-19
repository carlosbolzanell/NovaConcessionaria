package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.ArrayList;

public interface IGerente extends
        IVendedor {
    void registrarAutomovel(
            Automovel automovel);
    void removerAutomovel(
            Automovel automovel);
    void editarAutomovel(
            Automovel automovelAntigo, IBanco<Automovel, String> banco);
    void editarPreco(
            Automovel automovel,
            Double preco, IBanco<Automovel, String> banco);
    void registrarUsuario(
            Cliente cliente, IBanco<Cliente, Long> banco);
    String removerUsuario(
            Long cpf) throws ObjetoNaoEncontradoException;
    String editarUsuario(
            Long cpf,
            Cliente novoCliente, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException;
    ArrayList<Vendedor> verVendedores();
    ArrayList<net.weg.topcar.model.usuarios.Cliente> verClientes();
    ArrayList<String> verPagamentoVendedores();
    String verPagamentoVendedor(
            Vendedor vendedor);
}
