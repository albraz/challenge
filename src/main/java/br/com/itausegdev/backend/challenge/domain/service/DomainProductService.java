package br.com.itausegdev.backend.challenge.domain.service;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.application.dto.ProductResponse;
import br.com.itausegdev.backend.challenge.domain.builder.ProductBuilder;
import br.com.itausegdev.backend.challenge.domain.enums.ProductType;
import br.com.itausegdev.backend.challenge.infrastructure.entity.ProductEntity;
import br.com.itausegdev.backend.challenge.infrastructure.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.itausegdev.backend.challenge.domain.utils.Messages.*;

@Service
public class DomainProductService implements ProductService {

    private static final org.slf4j.Logger logger
            = org.slf4j.LoggerFactory.getLogger(DomainProductService.class);
    @Autowired
    private ProductRepository repository;

    /**
     * @param id product id
     * @return Builder Pattern
     */
    @Override
    public ProductBuilder getProduct(String id) {
        var response = repository.findProductsById(id);
        if (response == null) {
            logger.error(PRODUCT_NOT_FOUND);
            throw new RuntimeException();
        }
        return new ProductBuilder.Builder()
                .id(response.getId())
                .name(response.getName())
                .category(response.getCategory())
                .base(response.getBaseValue())
                .fee(response.getFeeValue())
                .build();
    }

    @Override
    public List<ProductResponse> getProducts() {
        var response = Optional.of(repository.findAll());
        if(response.get().isEmpty()) {
            logger.error(PRODUCT_NOT_FOUND);
            throw new IllegalArgumentException(PRODUCT_NOT_FOUND);
        }
        return response.get()
                .stream()
                .map(productEntity -> new ModelMapper().map(productEntity, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(ProductRequest request) {
        try {
            var fee = ProductType.valueOf(request.getCategory()).getFee(request.getBaseValue());
            var ent = builProductEntity(request, new ProductEntity(), fee);
            repository.save(ent);
        } catch (NullPointerException e) {
            throw new RuntimeException(INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(PRODUCT_NOT_PARAMETRIZED);
        }
    }

    @Override
    public void updateProduct(ProductRequest request) {
        try {
            var fee = ProductType.valueOf(request.getCategory()).getFee(request.getBaseValue());
            repository.updateProduct(request.getId(),
                    request.getName(),
                    request.getCategory(),
                    request.getBaseValue().toString(),
                    String.valueOf(fee));
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(PRODUCT_NOT_FOUND);
        }
    }

    private ProductEntity builProductEntity(ProductRequest request, ProductEntity product, BigDecimal fee) {
        product.setId(UUID.randomUUID().toString());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setBaseValue(request.getBaseValue().toString());
        product.setFeeValue(String.valueOf(fee));
        return product;
    }
}
