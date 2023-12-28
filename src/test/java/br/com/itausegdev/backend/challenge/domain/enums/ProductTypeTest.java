package br.com.itausegdev.backend.challenge.domain.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductTypeTest {

    @Test
    void shouldGetFeeValue() {
        assertThat(ProductType.VIDA.getFee(new BigDecimal("100.00")))
                .isEqualTo(new BigDecimal("103.2"));
        assertThat(ProductType.AUTO.getFee(new BigDecimal("50.00")))
                .isEqualTo(new BigDecimal("55.25"));
        assertThat(ProductType.VIAGEM.getFee(new BigDecimal("100.00")))
                .isEqualTo(new BigDecimal("107"));
        assertThat(ProductType.RESIDENCIAL.getFee(new BigDecimal("100.00")))
                .isEqualTo(new BigDecimal("107"));
        assertThat(ProductType.PATRIMONIAL.getFee(new BigDecimal("100.00")))
                .isEqualTo(new BigDecimal("108"));
    }
}
