package com.commerce.zero_cms_order_api.domain.repository;

import com.commerce.zero_cms_order_api.domain.model.ProductEntity;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductEntity> searchByProductName(String productName);
}
