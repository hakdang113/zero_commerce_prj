package com.commerce.zero_cms_order_api.redis;

import com.commerce.zero_cms_order_api.domain.form.AddProductCartForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@RedisHash("cart")
public class Cart {

    @Id
    private Long customerId; // 한 계정 당 하나의 장바구니를 가지는 것을 구분

    private List<Product> products = new ArrayList<>();
    private List<String> messages = new ArrayList<>();


    public void addMessage (String message) {
        messages.add(message);
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private Long id; // 상품 id
        private Long sellerId;
        private String name;
        private String description;
        private List<ProductItem> productItems = new ArrayList<>();


        public static Product from(AddProductCartForm form) {
            return Product.builder()
                    .id(form.getId())
                    .sellerId(form.getSellerId())
                    .name(form.getName())
                    .description(form.getDescription())
                    .productItems(form.getProductItems()
                            .stream().map(ProductItem::from)
                            .collect(Collectors.toList()))
                    .build();
        }
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductItem {
        private Long id;
        private String name;
        private Integer count;
        private Integer price;


        public static ProductItem from(AddProductCartForm.ProductItem form) {
            return ProductItem.builder()
                    .id(form.getId())
                    .name(form.getName())
                    .count(form.getCount())
                    .price(form.getPrice())
                    .build();
        }
    }
}