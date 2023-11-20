package com.commerce.zero_cms_order_api.application;

import com.commerce.zero_cms_order_api.domain.form.AddProductCartForm;
import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.model.ProductItemEntity;
import com.commerce.zero_cms_order_api.exception.CustomErrorCode;
import com.commerce.zero_cms_order_api.exception.CustomException;
import com.commerce.zero_cms_order_api.redis.Cart;
import com.commerce.zero_cms_order_api.service.CartService;
import com.commerce.zero_cms_order_api.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartApplication {

    private final ProductSearchService productSearchService;
    private final CartService cartService;


    public Cart addCart(Long customerId, AddProductCartForm form) {

        // 상품이 존재하는지 확인
        ProductEntity product = productSearchService.getByProductId(form.getId());
        if (product == null) { // 상품이 존재하지 않으면
            throw new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT);
        }
        // 상품이 존재할 때,

        // 장바구니 존재 확인
        Cart cart = cartService.getCart(customerId);
        if (cart != null && !addAble(cart, product, form)) { // 상품 아이템 수량이 부족한 경우
            throw new CustomException(CustomErrorCode.NOT_ENOUGH_ITEM_COUNT);
        }

        // 장바구니에 추가
        return cartService.addCart(customerId, form);
    }

    // 추가 가능한지 확인하는 메서드
    private boolean addAble(Cart cart, ProductEntity product, AddProductCartForm form) {
        Cart.Product cartProduct = cart.getProducts().stream().
                filter(p -> p.getId().equals(form.getId()))
                .findFirst().orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT));

        // 상품 아이템 수량이 있는지 확인
        Map<Long, Integer> cartItemCountMap = cartProduct.getProductItems().stream()
                .collect(Collectors.toMap(Cart.ProductItem::getId, Cart.ProductItem::getCount));

        Map<Long, Integer> currentItemCountMap = product.getProductItemEntities().stream()
                .collect(Collectors.toMap(ProductItemEntity::getId, ProductItemEntity::getCount));

        return form.getProductItems().stream().noneMatch(
                formItem -> {
                    Integer cartCount = cartItemCountMap.get(formItem.getId());
                    Integer currentCount = currentItemCountMap.get(formItem.getId());

                    return formItem.getCount() + cartCount > currentCount;
                });
    }
}
