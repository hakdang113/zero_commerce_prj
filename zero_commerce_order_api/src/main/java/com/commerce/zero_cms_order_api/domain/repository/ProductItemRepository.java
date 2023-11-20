package com.commerce.zero_cms_order_api.domain.repository;

import com.commerce.zero_cms_order_api.domain.model.ProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItemEntity, Long> {
}
