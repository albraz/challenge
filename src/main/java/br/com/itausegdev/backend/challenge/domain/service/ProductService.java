package br.com.itausegdev.backend.challenge.domain.service;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.application.dto.ProductResponse;
import br.com.itausegdev.backend.challenge.domain.builder.ProductBuilder;

import java.util.List;

public interface ProductService {
    ProductBuilder getProduct(String id);
    List<ProductResponse> getProducts();
    void createProduct(ProductRequest req);
    void updateProduct(ProductRequest request);
}
