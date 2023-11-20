package com.commerce.zero_cms_order_api.domain.repository;

import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.model.QProductEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImplements implements ProductRepositoryCustom{


    public JPAQueryFactory queryFactory;


    @Override
    public List<ProductEntity> searchByName(String name) {
        String search = "%" + name + "%";

        QProductEntity product = QProductEntity.productEntity;
        return queryFactory.selectFrom(product)
                .where(product.name.like(search))
                .fetch();
    }
}