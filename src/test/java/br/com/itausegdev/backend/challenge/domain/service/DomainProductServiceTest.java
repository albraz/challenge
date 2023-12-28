package br.com.itausegdev.backend.challenge.domain.service;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.infrastructure.entity.ProductEntity;
import br.com.itausegdev.backend.challenge.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DomainProductServiceTest {

    @InjectMocks
    private DomainProductService service;

    @Mock
    private ProductRepository repository;

    @Test
    void whenCreateProductVidaShouldCalculateFeeVida() {
        ProductRequest request = new ProductRequest();
        request.setName("Seguro de Vida Individual");
        request.setCategory("VIDA");
        request.setBaseValue(new BigDecimal("100.00"));

        var response = service.createProduct(request);

        assertEquals("103.20", response.getFeeValue());
    }

    @Test
    void whenCreateProductAutoShouldCalculateFeeAuto() {
        ProductRequest request = new ProductRequest();
        request.setName("Seguro Auto");
        request.setCategory("AUTO");
        request.setBaseValue(new BigDecimal("50.00"));

        var response = service.createProduct(request);

        assertEquals("55.25", response.getFeeValue());
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        ProductRequest request = new ProductRequest();
        request.setName("Seguro Residencial");
        request.setCategory("CASA");
        request.setBaseValue(new BigDecimal("100.00"));

        assertThrows(RuntimeException.class, () -> service.createProduct(request));
    }

    /**
     * Embora o retorno do método seja void, é possível verificar se o método calcula a taxa de seguro de vida.
     */
    @Test
    void whenUpdateProductVidaShouldCalculateFeeVida() {
        ProductRequest request = new ProductRequest();
        request.setId("d651b64e-1cb5-4019-a54f-01d47cf25911");
        request.setName("Seguro de Vida Individual");
        request.setCategory("VIDA");
        request.setBaseValue(new BigDecimal("100.00"));

        service.updateProduct(request);

        verify(repository).updateProduct("d651b64e-1cb5-4019-a54f-01d47cf25911",
                "Seguro de Vida Individual",
                "VIDA",
                "100.00",
                "103.20");
    }

    @Test
    void whenGetProductsShouldReturnListOfProducts() {
        List<ProductEntity> products = new ArrayList<>();
        ProductEntity product = new ProductEntity();
        product.setName("Seguro de Vida Individual");

        products.add(product);

        when(repository.findAll()).thenReturn(products);

        assertNotNull(service.getProducts());
    }
}
