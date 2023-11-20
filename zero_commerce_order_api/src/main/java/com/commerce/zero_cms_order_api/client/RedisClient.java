package com.commerce.zero_cms_order_api.client;

import com.commerce.zero_cms_order_api.exception.CustomErrorCode;
import com.commerce.zero_cms_order_api.exception.CustomException;
import com.commerce.zero_cms_order_api.redis.Cart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisClient {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    public <T> T get(Long key, Class<T> classType) {
        return get(key.toString(), classType);
    }

    private <T> T get(String key, Class<T> classType) {
        String redisValue = (String) redisTemplate.opsForValue().get(key);
        if (ObjectUtils.isEmpty(redisValue)) {
            return null;
        }
        try {
            return mapper.readValue(redisValue, classType);
        } catch (JsonProcessingException e) {
            log.error("Parsing error", e);
            return null;
        }
    }

    public void put(Long key, Cart cart) {
        put(key.toString(), cart);
    }

    private void put(String key, Cart cart) {
        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(cart));
        } catch (JsonProcessingException e) {
            throw new CustomException(CustomErrorCode.ADD_CART_FAILED);
        }
    }
}
