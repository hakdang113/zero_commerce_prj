package com.commerce.zero_cms_order_api.service;

import com.commerce.zero_cms_order_api.domain.form.RegisterProductItemForm;
import com.commerce.zero_cms_order_api.domain.form.UpdateProductItemForm;
import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.model.ProductItemEntity;
import com.commerce.zero_cms_order_api.domain.repository.ProductItemRepository;
import com.commerce.zero_cms_order_api.domain.repository.ProductRepository;
import com.commerce.zero_cms_order_api.exception.CustomErrorCode;
import com.commerce.zero_cms_order_api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductItemService {

    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;


    // 상품 아이템 추가
    @Transactional
    public ProductEntity registerProductItem (Long sellerId, RegisterProductItemForm form) {
        ProductEntity product = productRepository.findBySellerIdAndId(sellerId, form.getProductId())
                .orElseThrow((() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT)));

        // 상품의 아이템들이 같은 옵션명을 가지고 있는지 확인
        if (product.getProductItemEntities().stream()
                .anyMatch(item -> item.getName().equals(form.getName()))) {
            throw new CustomException(CustomErrorCode.SAME_ITEM_NAME_EXISTED);
        }

        ProductItemEntity productItem = ProductItemEntity.of(sellerId, form);
        product.getProductItemEntities().add(productItem);
        return product;
    }


    // 상품 아이템 수정
    @Transactional
    public ProductItemEntity updateProductItem (Long sellerId, UpdateProductItemForm form) {

        ProductItemEntity productItem = productItemRepository.findById(form.getId())
                .filter(pi -> pi.getSellerId().equals(sellerId))
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT_ITEM));
        productItem.setName(form.getName());
        productItem.setCount(form.getCount());
        productItem.setPrice(form.getPrice());
        return productItem;
    }


    // 상품 아이템 삭제
    @Transactional
    public void deleteProductItem (Long sellerId, Long productItemId) {
        ProductItemEntity productItem = productItemRepository.findById(productItemId)
                .filter(pi -> pi.getSellerId().equals(sellerId))
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT_ITEM));

        productItemRepository.delete(productItem);
    }
}
