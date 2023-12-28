package br.com.itausegdev.backend.challenge.domain.service;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.application.dto.ProductResponse;
import br.com.itausegdev.backend.challenge.domain.builder.ProductBuilder;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProducts();
    ProductResponse createProduct(ProductRequest req);
    void updateProduct(ProductRequest request);
}
