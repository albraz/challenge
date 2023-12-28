package br.com.itausegdev.backend.challenge.application.controller;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.application.dto.ProductResponse;
import br.com.itausegdev.backend.challenge.domain.service.DomainProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.itausegdev.backend.challenge.domain.utils.Messages.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private DomainProductService domainProductService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        logger.info(GET_PRODUCTS);
        return new ResponseEntity<>(domainProductService.getProducts(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest productRequest) {
        logger.info(CREATE_PRODUCT);
        var response = domainProductService.createProduct(productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ProductResponse> updateProduct(
            @Valid @RequestBody ProductRequest productRequest) {
        logger.info(UPDATE_PRODUCT);
        domainProductService.updateProduct(productRequest);
        return ResponseEntity.noContent().build();
    }
}
