package aula01;

import java.math.BigDecimal;
import java.util.Random;


public class ContaExemplo {
    private Integer numeroAgencia;
    private Integer numeroConta;
    private BigDecimal saldo;

    public ContaExemplo(BigDecimal saldo) {
        Random r = new Random();
        this.numeroAgencia = r.nextInt();
        this.numeroConta = r.nextInt();
        this.saldo = saldo;
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

    public BigDecimal getSaldo() {
        return this.saldo;
    }

}
