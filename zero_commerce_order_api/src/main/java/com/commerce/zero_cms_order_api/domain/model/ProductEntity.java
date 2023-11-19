package com.commerce.zero_cms_order_api.domain.model;

import com.commerce.zero_cms_order_api.domain.form.RegisterProductForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited // Entity 의 데이터가 변할 때마다 내용들을 저장함
@AuditOverride(forClass = BaseEntity.class)
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sellerId; // 판매자 id

    private String name; // 상품명

    private String description; // 상품 설명


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_Id")
    private List<ProductItemEntity> productItemEntities = new ArrayList<>(); // 상품 상세 정보


    // 변화
    public static ProductEntity of(Long sellerId, RegisterProductForm form) {
        return ProductEntity.builder()
                .sellerId(sellerId)
                .name(form.getName())
                .description(form.getDescription())
                .productItemEntities(form.getRegisterProductItems().stream()
                        .map(piFrom -> ProductItemEntity.of(sellerId, piFrom))
                        .collect(Collectors.toList()))
                .build();
    }

}
