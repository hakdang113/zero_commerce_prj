package com.commerce.zero_cms_order_api.service;

import com.commerce.zero_cms_order_api.domain.form.UpdateProductForm;
import com.commerce.zero_cms_order_api.domain.form.UpdateProductItemForm;
import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.form.RegisterProductForm;

import com.commerce.zero_cms_order_api.domain.model.ProductItemEntity;
import com.commerce.zero_cms_order_api.domain.repository.ProductRepository;
import com.commerce.zero_cms_order_api.exception.CustomErrorCode;
import com.commerce.zero_cms_order_api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    // 상품 추가 메서드
    @Transactional
    public ProductEntity registerProduct(Long sellerId, RegisterProductForm form) {
        return productRepository.save(ProductEntity.of(sellerId, form));
    }


    // 상품 수정 메서드
    @Transactional
    public ProductEntity updateProduct(Long sellerId, UpdateProductForm form) {
        ProductEntity product = productRepository.findBySellerIdAndId(sellerId, form.getId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT));

        product.setName(form.getName());
        product.setDescription(form.getDescription());

        for (UpdateProductItemForm itemForm : form.getUpdateProductItems()) {
            ProductItemEntity productItem = product.getProductItemEntities().stream()
                    .filter(pi -> pi.getId().equals(itemForm.getId()))
                    .findFirst().orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT_ITEM));
            productItem.setName(itemForm.getName());
            productItem.setPrice(itemForm.getPrice());
            productItem.setCount(itemForm.getCount());
        }

        return product;
    }

}
