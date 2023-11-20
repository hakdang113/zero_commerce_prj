package com.commerce.zero_cms_order_api.service;

import com.commerce.zero_cms_order_api.client.RedisClient;
import com.commerce.zero_cms_order_api.domain.form.AddProductCartForm;
import com.commerce.zero_cms_order_api.redis.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    // redis 사용
    private final RedisClient redisClient;

    // 장바구니 상품 추가
    public Cart addCart(Long customerId, AddProductCartForm form) {

        Cart cart = redisClient.get(customerId, Cart.class);

        if (cart == null) { // 장바구니가 없는 경우
            cart = new Cart();
            cart.setCustomerId(customerId);
        }

        // 장바구니가 있을 때,
        Optional<Cart.Product> productOptional = cart.getProducts().stream()
                .filter(product -> product.getId().equals(form.getId()))
                .findFirst();

        // 이전에 장바구니에 같은 상품을 추가한 경우
        if (productOptional.isPresent()) {
            Cart.Product redisProduct = productOptional.get();
            List<Cart.ProductItem> productItems = form.getProductItems()
                    .stream().map(Cart.ProductItem::from)
                    .collect(Collectors.toList());
            Map<Long, Cart.ProductItem> redisItemMap = redisProduct.getProductItems().stream()
                    .collect(Collectors.toMap(Cart.ProductItem::getId, item -> item));


            // redis의 productName과 form의 productName이 다른 경우
            if (!redisProduct.getProductName().equals(form.getProductName())) {
                cart.addMessage(redisProduct.getProductName() + " 상품 정보가 변경되었습니다.");
            }

            for (Cart.ProductItem productItem : productItems) {
                Cart.ProductItem redisProductItem = redisItemMap.get(productItem.getId()); // redis에서 상품 아이템을 가져옴

                if (redisProductItem == null) { // 없으면
                    // 추가
                    redisProduct.getProductItems().add(productItem);
                } else { // 있으면
                    if (!redisProductItem.getPrice().equals(productItem.getPrice())) { // 가격 변경
                        cart.addMessage(
                                redisProduct.getProductName() + productItem.getItemNameWithSize() + "의 가격이 변경되었습니다."
                        );
                    }
                    redisProductItem.setCount(redisProductItem.getCount() + productItem.getCount()); // 수량 변경
                }
            }
        } else {
            // 이전에 장바구니에 추가한 상품이 아닌 경우
            Cart.Product product = Cart.Product.from(form);
            cart.getProducts().add(product); // 장바구니에 추가
        }
        redisClient.put(customerId, cart); // 해당 고객 장바구니에 추가
        return cart;
    }

    public Cart getCart(Long customerId) {
        return redisClient.get(customerId, Cart.class);
    }
}
