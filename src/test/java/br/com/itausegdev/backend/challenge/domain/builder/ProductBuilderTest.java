package br.com.itausegdev.backend.challenge.domain.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductBuilderTest {

    @Test
    void shouldBuildProduct() {
        var build = new ProductBuilder.Builder()
                .id("1")
                .name("Seguro de Vida Individual")
                .category("VIDA")
                .base("100.00")
                .fee("103.20")
                .build();

        assertEquals("1", build.getId());
        assertEquals("Seguro de Vida Individual", build.getName());
        assertEquals("VIDA", build.getCategory());
        assertEquals("100.00", build.getBase());
        assertEquals("103.20", build.getFee());
    }
}
