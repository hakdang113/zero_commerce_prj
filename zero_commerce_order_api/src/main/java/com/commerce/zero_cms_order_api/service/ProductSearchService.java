package com.commerce.zero_cms_order_api.service;

import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.repository.ProductRepository;
import com.commerce.zero_cms_order_api.exception.CustomErrorCode;
import com.commerce.zero_cms_order_api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductRepository productRepository;

    // 각 상품의 코드(productId)에 따라 상품을 검색
    // 상품 상세 정보까지 보여줌
    public ProductEntity getByProductId (Long productId) {
        return productRepository.findWithProductItemsById(productId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT));
    }

    // 목록
    public List<ProductEntity> getListByProductIds (List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }

    // 검색
    public List<ProductEntity> searchByProductName(String productName) {
        return productRepository.searchByProductName(productName);
    }
}
