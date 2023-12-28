package br.com.itausegdev.backend.challenge.domain.service;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.application.dto.ProductResponse;
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

import static br.com.itausegdev.backend.challenge.domain.utils.Message.PRODUCT_NOT_FOUND;
import static br.com.itausegdev.backend.challenge.domain.utils.Message.PRODUCT_NOT_PARAMETRIZED;

@Service
public class DomainProductService implements ProductService {

    private static final org.slf4j.Logger logger
            = org.slf4j.LoggerFactory.getLogger(DomainProductService.class);
    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductResponse> getProducts() {
        var response = Optional.of(repository.findAll());

        if (response.get().isEmpty()) {
            logger.error(PRODUCT_NOT_FOUND);
            throw new IllegalArgumentException(PRODUCT_NOT_FOUND);
        }
        return response.get()
                .stream()
                .map(productEntity -> new ModelMapper()
                        .map(productEntity, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        try {
            var fee = ProductType.valueOf(request.getCategory())
                    .getFee(request.getBaseValue());
            var ent = builProductEntity(request, new ProductEntity(), fee);

            repository.save(ent);

            return new ModelMapper().map(ent, ProductResponse.class);
        } catch (IllegalArgumentException e) {
            logger.error(PRODUCT_NOT_PARAMETRIZED);
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
