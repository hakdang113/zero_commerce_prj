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
        private String brand;
        private String productName;
        private String description;
        private List<ProductItem> productItems = new ArrayList<>();


        public static Product from(AddProductCartForm form) {
            return Product.builder()
                    .id(form.getId())
                    .sellerId(form.getSellerId())
                    .brand(form.getBrand())
                    .productName(form.getProductName())
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
        private String itemNameWithSize; // '상품명_사이즈'
        private Integer price; // 가격
        private Integer count; // 0 ~ 999개
        private String season; // '년도/시즌'
        private String sex; // 남성용(M), 여성용(W), 공용(MW)
        private String category; // 카테고리


        public static ProductItem from(AddProductCartForm.ProductItem form) {
            return ProductItem.builder()
                    .id(form.getId())
                    .itemNameWithSize(form.getItemNameWithSize())
                    .price(form.getPrice())
                    .count(form.getCount())
                    .season(form.getSeason())
                    .sex(form.getSex())
                    .category(form.getCategory())
                    .build();
        }
    }
}