package com.commerce.zero_cms_order_api.domain.repository;

import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.model.QProductEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImplements implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public List<ProductEntity> searchByProductName(String productName) {
        String search = "%" + productName + "%";

        QProductEntity product = QProductEntity.productEntity;
        return queryFactory.selectFrom(product)
                .where(product.name.like(search))
                .fetch();
    }
}