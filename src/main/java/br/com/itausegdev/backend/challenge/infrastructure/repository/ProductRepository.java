package br.com.itausegdev.backend.challenge.infrastructure.repository;

import br.com.itausegdev.backend.challenge.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.name = :name, p.category = :category, " +
            "p.baseValue = :baseValue, p.feeValue = :feeValue WHERE p.id = :id")
    void updateProduct(@Param("id") String id,
                                  @Param("name") String name,
                                  @Param("category") String category,
                                  @Param("baseValue") String baseValue,
                                  @Param("feeValue") String feeValue);
}
