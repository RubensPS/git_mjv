package aula01;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class ApresentaçãoBigDecimal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        Overview:
        Faz parte do pacote java.math (não confundir com a classe Math, que faz parte do pacote java.lang.
        Foi introduzida no Java SE 1.1.
        A classe BigDecimal representa números decimais imutáveis de precisão arbitrária.
        O que isso significa:
        - Imutável, pois após criado o objeto, seu estado não pode ser alterado;
        - Precisão arbitrária, pois diferente dos números primitivos em Java, a sua limitação é o espaço de memória disponível.

        Representação:
        Consiste de duas partes
        - Um número inteiro de precisão arbitrária;
        - Um numero que indica a quantidade de casas decimais;
        Exemplo
        inteiro: 58921
        precisao: 3
        resultado: 58.921

        Curiosidade:
        - Existem vários tipos de arredondamentos que podem ser utilizados ao construir o objeto, a partir do ENUM RoundingMode ou da classe MathContext, entre eles o HALF_EVEN,
          que é o padrão utilizado pelo BC para operações financeiras (ABNT 5891).

        Porque utilizar o BigDecimal?
        Seus cálculos são mais precisos que os realizados com os tipos primitivos como float e double. Isso acontece devido a forma
        que os computadores fazem operações com números binários, e não por um problema específico da linguagem Java.
        Dessa forma, para cálculos monetários, que necessitam dessa exatidão,deve-se utilizar o BigDecimal.

        Desvantagens:
        1 - Operações com objetos são mais lentas que com os primitivos;
        2 - Requer mais memória e processamento, pois mais objetos devem ser instanciados durante as operações, devido à imutabilidade;
        3 - Sua utilização requer um conhecimento melhor da classe e seus métodos, sendo mais complexo que operadores aritméticos comuns;

        Formas de instanciamento:
       Para criar um BigDecimal, podem ser utilizados tanto construtores quanto métodos estáticos a partir de valores de outro tipo.
       Há diversos métodos construtores, havendo sobrecarga dos métodos.
       Não devemos instanciar a partir do construtor, utilizando outros tipos que não String como parâmetro.
       Exemplos:
        */
        BigDecimal A = new BigDecimal("0.03"); // Construtor String
        BigDecimal B = new BigDecimal(0.03d); //Construtor double
        BigDecimal C = BigDecimal.valueOf(0.03); //método estático convertendo double. Forma correta a partir de tipos diferentes de String
        BigDecimal D = new BigDecimal("0.123456", MathContext.DECIMAL32); //Construtor com dois parâmetros, sendo o segundo para definição de casas decimais e forma de arredondamento

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Exemplo de porque não instanciar por outros tipos usando construtor:");
        System.out.println("Valor por new BigDecimal(double valor): " + B);
        System.out.println("Valor por BigDecimal.valueOf(double valor): " + C);
        System.out.println("---------------------------------------------------------------------------------------");
        scanner.nextLine();
        /*
        Métodos oferecidos pela classe e métodos principais:
        A classe BigDecimal nos oferece as seguintes operações:
        - aritméticas;
        - manipulação de casas decimais;
        - arredondamento;
        - comparação;
        - hashing;
        - conversão de tipos.

        Vamos apresentar aqui três métodos mais utilizados e alguns problemas que podem surgir:
        método subtract();
        - realiza operações de subtração entre dois objetos BigDecimal;
        - Retorna um BigDecimal cujo valor é a subtração entre o valor da instância que chamou o método e o valor passado como parâmetro.
        Aproveitamos o exemplo para mostrar a diferença de precisão de operações entre valores de ponto flutuante e do BigDecimal:
         */

        Double a = 0.03;
        Double b = 0.02;
        BigDecimal bd1 = new BigDecimal("0.03");
        BigDecimal bd2 = new BigDecimal("0.02");
        BigDecimal bd3 = new BigDecimal(0.03);
        BigDecimal bd4 = new BigDecimal(0.02);

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Exemplo da precisão com subtração e construtores a partir de valores primitivos:");
        System.out.println("Subtração com variáveis tipo Double: " + (a-b));
        System.out.println("Subtração com BigDecimal: " + bd1.subtract(bd2));
        System.out.println("Subtração com BigDecimal, construídos com valores primitivos: " + bd3.subtract(bd4));
        System.out.println("---------------------------------------------------------------------------------------");
        scanner.nextLine();

        /*
        Método divide():
        - Realiza operações de divisão entre dois objetos BigDecimal;
        - Retorna um BigDecimal cujo valor é a divisão entre o valor da instância que chamou o método e o valor passado como parâmetro.
        Aproveitamos o exemplo para trazer um problema de utilizar esse método sem definir as casas decimais.
         */

        BigDecimal bd5 = new BigDecimal("0.03");
        BigDecimal bd6 = new BigDecimal("0.02");

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Exemplo da divisão e problemas em não delimitar as casas decimais:");
        System.out.println("Divisão com BigDecimal. Casas decimais definidas em 7: " + bd6.divide(bd5, MathContext.DECIMAL32));
        System.out.println("Divisão com BigDecimal. Casas decimais não definidas");
        try {
            System.out.println(B.divide(A));
        } catch (ArithmeticException e) {
            System.out.println("Erro - dízima periódica");
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------------------------------------");
        scanner.nextLine();

        /*
        Método compareTo():
        - Compara os valores de dois objetos BigDecimal;
        - Retorna -1, 0 ou 1 se o resultado da comparação entre o objeto que chama o método for, respectivamente, menor,
        igual ou maior que o passado como parâmetro.
        Aproveitamos o exemplo mostrar porque não devemos utilizar o método equals() herdado de Object para fazer a
        comparação de BigDecimal. O método equals() irá comparar os valores, considerando, inclusive, o número de casas decimais,
        o que pode gerar erros mesmo com números que são, de fato iguais.
         */

        BigDecimal bd7 = new BigDecimal("0.03");
        BigDecimal bd8 = new BigDecimal("0.02");
        BigDecimal bd9 = new BigDecimal("0.030");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Exemplos de comparação com o método compareTo() e problema com o equals():");
        System.out.println("Comparação de números iguais utilizando o equals(): " + bd7.equals(bd9));
        System.out.println("Comparação de números iguais utilizando o compareTo(): " + bd7.compareTo(bd9));
        System.out.println("Comparação de números diferentes utilizando o compareTo(): " + bd7.compareTo(bd8));
        System.out.println("---------------------------------------------------------------------------------------");
        scanner.nextLine();

        scanner.close();
    }


    /*
    OUTROS EXEMPLOS:
    1) Soma sequencial com primitivos:
    Double total = 0d;
        for (int i=0 ; i<10 ; i++) {
            total += a;
        }
        System.out.println(total);

    2) Utilizando um caso real. A classe ContaExemplo e suas operações:
        ContaExemplo conta01 = new ContaExemplo(new BigDecimal("100.00"));
        ContaExemplo conta02 = new ContaExemplo(new BigDecimal("50.00"));
        System.out.println(conta01.getSaldo());
        conta01.sacar(BigDecimal.valueOf(20.37));
        System.out.println(conta01.getSaldo());

     */

}
