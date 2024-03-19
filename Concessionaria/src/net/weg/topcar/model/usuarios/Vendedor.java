package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.Automovel;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

public class Vendedor extends Cliente implements IVendedor {

    private Double salario;
    private Double comissoes;

    public Vendedor(String nome, Long cpf, String senha, Integer idade, Double salario) {
        super(nome, cpf, senha, idade);
        this.salario = salario;
    }

    public String menu() {
        return super.menu() + """
                4 - Vender automóvel;
                5 - Procurar usuário;
                6 - Ver pagamento;
                """;
    }

    @Override
    public void vender(Automovel automovel, Cliente cliente) {
        cliente.adicionarProprioAutomovel(automovel);
        this.comissoes += ((automovel.getPreco() * 0.01));
    }

//    @Override
//    public void vender(Automovel automovel, Cliente cliente) {
//
//    }

    @Override
    public Cliente buscarUsuario(Long cpf, IBanco<Cliente, Long> banco) throws ObjetoNaoEncontradoException {
        return banco.buscarUm(cpf);
    }

    public String verPagamento() {
        return ("R$ " + (salario + comissoes));
    }


    @Override
    public String toString() {
        return super.toString() +
                "\nSalário: R$ " + this.salario +
                "\nComissões: R$ " + this.comissoes;
    }
}
