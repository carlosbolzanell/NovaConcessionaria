package net.weg.topcar.model.usuarios;

import net.weg.topcar.dao.IBanco;
import net.weg.topcar.model.automoveis.Automovel;
import net.weg.topcar.model.exceptions.FalhaNaCompraException;
import net.weg.topcar.model.exceptions.ObjetoNaoEncontradoException;

public class Vendedor extends Cliente implements IVendedor {

    private Double salario;
    private Double comissoes;
    private Double comissao;

    public Vendedor(String nome, Long cpf, String senha, Long idade, Double salario) {
        super(nome, cpf, senha, idade);
        this.salario = salario;
        this.comissao = 0.01;
    }

    public String menu() {
        return super.menu() + """
                4 - Vender automóvel;
                5 - Procurar usuário;
                6 - Ver pagamento;
                """;
    }

    @Override
    public void vender(Automovel automovel, Cliente cliente) throws FalhaNaCompraException {
        if(automovel.isComprado()){
            throw new FalhaNaCompraException("O carro já foi comprado");
        }
        cliente.adicionarProprioAutomovel(automovel);
        automovel.mudarStatus();
        this.comissoes += ((automovel.getPreco() * comissao));
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

    protected String verPagamentoComNome() {
        return this.getNome() + " : " + this.verPagamento();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSalário: R$ " + this.salario +
                "\nComissões: R$ " + this.comissoes;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public Double getSalario() {
        return this.salario;
    }
}
