package com.commerce.zero_cms_order_api.domain.repository;

import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    // Lazy 에러 해결
    // id를 이용해서 ProductItems를 같이 가져옴
    // ProductEntity에 있는 productItemEntities 를 같이 로드함
    @EntityGraph(attributePaths = {"productItemEntities"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<ProductEntity> findWithProductItemsById(Long id);


}
