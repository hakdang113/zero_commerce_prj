package com.commerce.zero_cms_order_api.service;

import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.RegisterProductForm;

import com.commerce.zero_cms_order_api.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    @Transactional
    public ProductEntity registerProduct(Long sellerId, RegisterProductForm form) {
        return productRepository.save(ProductEntity.of(sellerId, form));
    }


}
