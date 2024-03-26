package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Vendedor implements IGerente{

    public Gerente(String nome, Long cpf, String senha, Long idade, Double salario) {
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

    public String editarUsuario( Cliente clienteEditado, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        banco.alterar(clienteEditado.getCpf(), clienteEditado);
        return "Usuário Editado!";
    }
    public String registrarAutomovel(Automovel automovel, IBanco<Automovel, String> banco) {
        banco.adicionar(automovel);
        return "Automóvel registrado!";
    }

    public String removerAutomovel(String codigo, IBanco<Automovel, String> banco) throws ObjetoNaoEncontradoException {
        banco.remover(codigo);
        return "Automóvel removido";
    }

    /**
     * Método responsável por executar a ação de ediçao de um automóvel em nível de repositório (DAO).
     * O parametro de automóvel recebe as informações editadas do automóvel, já o parametro
     * de banco recebe qual o repositório que manipula objetos do tipo Automóvel
     * O id do automóvel permanecerá o mesmo, por esse motivo é possível pegar o mesmo id
     * presente no objeto editado
     * @param automovel
     * @param banco
     * @throws ObjetoNaoEncontradoException
     */
    public String editarAutomovel(Automovel automovel,
                                IBanco<Automovel, String> banco) throws ObjetoNaoEncontradoException {
        banco.alterar(automovel.getCODIGO(), automovel);
        return "Automóvel alterado";
    }

    public String editarPreco(String codigo, Double preco, IBanco<Automovel, String> banco) throws ObjetoNaoEncontradoException {
        Automovel automovel = banco.buscarUm(codigo);
        automovel.setPreco(preco);
        banco.alterar(codigo, automovel);
        return "Preço do automóvel editado";
    }

    public String registrarUsuario(Cliente cliente, IBanco<Cliente, Long> banco) {
        if(!(cliente instanceof Gerente)){
            banco.adicionar(cliente);
            return "Usuário registrado";
        }
        throw new RuntimeException("Ação não autorizada!");
    }

    public String removerUsuario(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        Cliente clienteRemover = banco.buscarUm(cpf);
        if (!(clienteRemover instanceof Gerente)) {
            banco.remover(clienteRemover.getCpf());
            return ("Usuário removido!");
        }
        throw new RuntimeException("Ação não autorizada");
    }

    public List<Vendedor> verVendedores(IBanco<Cliente, Long> banco) {
//        List<Cliente> listaVendedores = banco.buscarTodos();
//        listaVendedores.removeIf(cliente ->  !(cliente instanceof Vendedor));
        List<Cliente> listaClientes = banco.buscarTodos();
        List<Vendedor> listaVendedores = new ArrayList<>();
        listaClientes.forEach(cliente -> {
            if(cliente instanceof Vendedor vendedor){
                listaVendedores.add(vendedor);
            }
        });
        return listaVendedores;
    }

    public List<Cliente> verClientes(IBanco<Cliente, Long> banco) {
        return banco.buscarTodos();
    }

    public List<String> verPagamentoVendedores(IBanco<Cliente, Long> banco) {
        List<String> listaPagamentos = new ArrayList<>();
        for (Vendedor vendedor : verVendedores(banco)) {
            listaPagamentos.add(vendedor.verPagamentoComNome());
        }
        return listaPagamentos;
    }
    public String verPagamentoVendedor(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        Cliente cliente = banco.buscarUm(cpf);
        if(cliente instanceof Vendedor vendedor){
            return vendedor.verPagamentoComNome();
        }
        throw new RuntimeException("O usuário informado não é um vendedor!");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
