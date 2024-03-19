package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.ArrayList;

public class Gerente extends Vendedor implements IGerente{

    public Gerente(String nome, Long cpf, String senha, Integer idade, Double salario) {
        super(nome, cpf, senha, idade, salario);
    }

    public String menu() {
        return super.menu() +
                """
                7 - Registrar automóvel;
                8 - Remover automóvel;
                9 - Editar automóvel;
                10 - Editar preço;
                11 - Registrar usuário;
                12 - Remover usuário;
                13 - Editar usuário;
                14 - Ver vendedores;
                15 - Ver clientes;
                16 - Ver pagamentos dos vendedores;
                17 - Ver pagamento de um vendedor;
                """;
    }

    public String editarUsuario(Long cpf, Cliente clienteEditado, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        banco.alterar(cpf, clienteEditado);
        return "Usuário Editado!";
    }

//    @Override
//    public void venderAutomovel(Automovel automovel, Cliente cliente) {
//        cliente.adicionarProprioAutomovel(automovel);
//        this.setComissoes(automovel.getPreco() * 0.02);
//    }

    public void registrarAutomovel(Automovel automovel) {
        automovel.adicionarAutomovel();
    }

    public void removerAutomovel(Automovel automovel) {
        automovel.removerAutomovel();
    }

    public void editarAutomovel(Automovel automovelAntigo,
                                Automovel automovelNovo) {
        automovelAntigo.editarAutomovel(automovelNovo);
    }

    public void editarPreco(Automovel automovel, double preco) {
        automovel.setPreco(preco);
    }

    public void registrarUsuario(Cliente cliente) {
        cliente.adicionarUsuario();
    }

    public String removerUsuario(String cpf) {
        Cliente clienteRemover = Cliente.procurarUsuario(cpf);
        if (clienteRemover == null) {
            return ("Usuário não encontrado!");
        } else if (clienteRemover instanceof Gerente) {
            return ("O usuário pesquisado é um gerente! Impossível fazer a remoção!");
        }
        clienteRemover.removerUsuario();
        return ("Usuário removido!");
    }

//    public void editarUsuario(Usuario usuarioAntigo, Usuario usuarioNovo) {
//        usuarioAntigo.editarUsuario(usuarioNovo);
//    }

    public ArrayList<Vendedor> verVendedores() {
        ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();
        for (Cliente cliente : getListaUsuarios()) {
            if (cliente instanceof Vendedor vendedor) {
                listaVendedores.add(vendedor);
            }
        }
        return listaVendedores;
    }

    public ArrayList<net.weg.topcar.model.usuarios.Cliente> verClientes() {
        ArrayList<net.weg.topcar.model.usuarios.Cliente> listaClientes = new ArrayList<net.weg.topcar.model.usuarios.Cliente>();

        for (Cliente usuario : getListaUsuarios()) {
            if (usuario instanceof net.weg.topcar.model.usuarios.Cliente cliente) {
                listaClientes.add(cliente);
            }
        }

        return listaClientes;
    }

    public ArrayList<String> verPagamentoVendedores() {
        ArrayList<String> listaPagamentos = new ArrayList<String>();

        for (Vendedor vendedor : verVendedores()) {
            listaPagamentos.add(verPagamentoVendedor(vendedor));
        }

        return listaPagamentos;
    }

    public String verPagamentoVendedor(Vendedor vendedor) {
        return vendedor.getNome() + " : " + vendedor.verPagamento();
    }

    @Override
    public String toString() {
        return "Gerente {" +
                "\nNome: " + getNome() +
                "\nCPF: " + getCpf() +
                "\nIdade: " + getIdade() +
                "\nSalário: R$ " + getSalario() +
                "\nComissões: R$ " + getComissoes() + " }\n";
    }
}
