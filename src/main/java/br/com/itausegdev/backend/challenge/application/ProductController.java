package br.com.itausegdev.backend.challenge.application;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.application.dto.ProductResponse;
import br.com.itausegdev.backend.challenge.domain.builder.ProductBuilder;
import br.com.itausegdev.backend.challenge.domain.service.DomainProductService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductBuilder> getProductById(@RequestParam String id) {
        logger.info(GET_PRODUCT_BY_ID);
        return new ResponseEntity<>(domainProductService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        logger.info(GET_PRODUCTS);
        return new ResponseEntity<>(domainProductService.getProducts(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createProduct(
            @RequestBody ProductRequest productRequest) {
        logger.info(CREATE_PRODUCT);
        domainProductService.createProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest) {
        logger.info(UPDATE_PRODUCT);
        domainProductService.updateProduct(productRequest);
        return ResponseEntity.noContent().build();
    }
}
