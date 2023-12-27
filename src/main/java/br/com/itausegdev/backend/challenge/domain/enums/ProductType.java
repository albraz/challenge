package br.com.itausegdev.backend.challenge.domain.enums;

import java.math.BigDecimal;

/**
 * ProductType Enum
 * O método getFee() calcula o valor da taxa conforme o tipo de produto.
 * O ideal seria que o valor da taxa fosse persistido em um banco de dados.
 * 
 * A desvantagem de utilizar um Enum é que a taxa não pode ser alterada em tempo de execução
 * e não é possível adicionar novos tipos de produtos sem alterar o código-fonte, aumentado o acoplamento.
 *
 * A vantagem é que o Enum é uma classe final, portanto não pode ser extendida, e é thread-safe.
 * Outra vantagem é chamar o método getFee() diretamente no Enum, sem a necessidade de instanciar um objeto.
 */
public enum ProductType {
    VIDA("Vida", BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.2), BigDecimal.valueOf(0.0)),
    AUTO("Auto", BigDecimal.valueOf(5.5), BigDecimal.valueOf(4), BigDecimal.valueOf(1)),
    VIAGEM("Viagem", BigDecimal.valueOf(2), BigDecimal.valueOf(4), BigDecimal.valueOf(1)),
    RESIDENCIAL("Residencial", BigDecimal.valueOf(4), BigDecimal.valueOf(0), BigDecimal.valueOf(3)),
    PATRIMONIAL("Patrimonial", BigDecimal.valueOf(5), BigDecimal.valueOf(3), BigDecimal.valueOf(0));

    private String category;
    private BigDecimal iof;
    private BigDecimal pis;
    private BigDecimal cofins;

    ProductType(String category, BigDecimal iof, BigDecimal pis, BigDecimal cofins) {
        this.category = category;
        this.iof = iof;
        this.pis = pis;
        this.cofins = cofins;
    }

    public BigDecimal getFee(BigDecimal base) {

        BigDecimal hundred = BigDecimal.valueOf(100).setScale(2);

        return base.add(base.multiply(this.iof).divide(hundred))
                .add(base.multiply(this.pis).divide(hundred))
                .add(base.multiply(this.cofins).divide(hundred));
    }
}