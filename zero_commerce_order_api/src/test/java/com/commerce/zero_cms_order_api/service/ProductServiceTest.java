package com.commerce.zero_cms_order_api.service;

import com.commerce.zero_cms_order_api.domain.RegisterProductForm;
import com.commerce.zero_cms_order_api.domain.RegisterProductItemForm;
import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import com.commerce.zero_cms_order_api.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {


    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void ADD_PRODUCT_TEST() {
        Long sellerId = 1L;

        RegisterProductForm form = makeProductForm("아디다스 삼바", "아디다스 신발입니다", 5);

        ProductEntity p = productService.registerProduct(sellerId, form);

        ProductEntity result = productRepository.findWithProductItemsById(p.getId()).get();

        assertNotNull(result);


        assertEquals(result.getName(), "아디다스 삼바");
        assertEquals(result.getDescription(), "아디다스 신발입니다");
        assertEquals(result.getProductItemEntities().size(), 5);
        assertEquals(result.getProductItemEntities().get(0).getName(), "아디다스 삼바0");
        assertEquals(result.getProductItemEntities().get(0).getPrice(), 10000);
        assertEquals(result.getProductItemEntities().get(0).getCount(), 1);

    }


    private static RegisterProductForm makeProductForm(String name, String description, int itemCount) {
        List<RegisterProductItemForm> registerProductItemForms = new ArrayList<>();

        for (int i = 0; i < itemCount; i++) {
            registerProductItemForms.add(makeProductItemForm(null, name + i));
        }

        return RegisterProductForm.builder()
                .name(name)
                .description(description)
                .registerProductItems(registerProductItemForms)
                .build();

    }

    private static RegisterProductItemForm makeProductItemForm(Long productId, String name) {
        return RegisterProductItemForm.builder()
                .productId(productId)
                .name(name)
                .price(10000)
                .count(1)
                .build();
    }
}