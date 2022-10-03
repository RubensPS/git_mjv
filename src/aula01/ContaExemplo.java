package aula01;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;


public class ContaExemplo {
    private Integer numeroAgencia;
    private Integer numeroConta;
    private BigDecimal saldo;
    private Emprestimo emprestimoPessoal ;

    public ContaExemplo(BigDecimal saldo) {
        Random r = new Random();
        this.numeroAgencia = r.nextInt();
        this.numeroConta = r.nextInt();
        this.saldo = saldo;
        this.emprestimoPessoal = new Emprestimo();
    }

    public void depositar(BigDecimal valor) {
       saldo = saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (saldo.compareTo(valor) != -1) {
            saldo = saldo.subtract(valor);
        } else System.out.println("Saldo insuficiente para saque!");
    }

    public void transferir(BigDecimal valor, ContaExemplo contaDestino) {
        if (saldo.compareTo(valor) != -1) {
            saldo = saldo.subtract(valor);
            contaDestino.saldo = contaDestino.saldo.add(valor);
        } else System.out.println("Saldo insuficiente para transferência!");
    }

    public void emprestar(BigDecimal valor, int parcelas) {
        saldo = saldo.add(valor.setScale(2, RoundingMode.HALF_EVEN));
        emprestimoPessoal.setValorInicial(valor.setScale(2, RoundingMode.HALF_EVEN));
        emprestimoPessoal.setParcelas(parcelas);
        emprestimoPessoal.setSaldoDevedor(valor.multiply(BigDecimal.valueOf(1.1)));
        emprestimoPessoal.setPrestacao(emprestimoPessoal.getSaldoDevedor()
                .divide(BigDecimal.valueOf(parcelas), new MathContext(2, RoundingMode.HALF_EVEN)));
    }

    public void getEmprestimoPessoal() {
        System.out.println("Dados do Empréstimo:");
        System.out.println("Valor depositado: " + emprestimoPessoal.getValorInicial());
        System.out.println("Parcelas: " + emprestimoPessoal.getParcelas());
        System.out.println("Valor da prestação: " + emprestimoPessoal.getPrestacao().setScale(2, RoundingMode.HALF_EVEN));
        System.out.println("Saldo devedor: " + emprestimoPessoal.getSaldoDevedor());
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

}
