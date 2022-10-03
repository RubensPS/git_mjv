package aula01;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class Emprestimo {
    private BigDecimal valorInicial;
    private int parcelas;
    private BigDecimal prestacao;
    private BigDecimal saldoDevedor;

}
