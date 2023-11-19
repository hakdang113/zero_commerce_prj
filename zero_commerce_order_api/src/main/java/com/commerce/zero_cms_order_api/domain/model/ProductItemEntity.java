package com.commerce.zero_cms_order_api.domain.model;

import com.commerce.zero_cms_order_api.domain.form.RegisterProductItemForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class ProductItemEntity extends BaseEntity {

    // 상품 상세 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sellerId;

    @Audited
    private String name; // 상품명

    @Audited
    private Integer price; // 가격

    private Integer count; // 수량


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;


    public static ProductItemEntity of(Long sellerId, RegisterProductItemForm form) {
        return ProductItemEntity.builder()
                .sellerId(sellerId)
                .name(form.getName())
                .price(form.getPrice())
                .count(form.getCount())
                .build();
    }
}
