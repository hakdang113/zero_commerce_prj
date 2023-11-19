package com.commerce.zero_cms_user_api.service.seller;

import com.commerce.zero_cms_user_api.domain.model.SellerEntity;
import com.commerce.zero_cms_user_api.domain.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Optional<SellerEntity> findByIdAndEmail(Long id, String email) {
        return sellerRepository.findByIdAndEmail(id, email);

        // CustomerService 부분과 다르게 SellerRepository를 수정해서 stream.filter 부분을 생략할 수 있도록 함
    }


    public Optional<SellerEntity> findValidSeller(String email, String password) {
        return sellerRepository.findByEmailAndUserPasswordAndVerifyIsTrue(email, password);

        // CustomerService 부분과 다르게 SellerRepository를 수정해서 stream.filter 부분을 생략할 수 있도록 함
    }

}