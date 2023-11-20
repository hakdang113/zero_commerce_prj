package com.commerce.zero_cms_user_api.service.seller;

import com.commerce.zero_cms_user_api.domain.form.UpdateForm;
import com.commerce.zero_cms_user_api.domain.model.SellerEntity;
import com.commerce.zero_cms_user_api.domain.repository.SellerRepository;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerUpdateService {

    private final SellerRepository sellerRepository;


    @Transactional
    public SellerEntity updateSeller(String email, UpdateForm form) {
        SellerEntity seller = sellerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER));

        seller.setUserPassword(form.getUserPassword());
        seller.setAddress(form.getAddress());
        seller.setCellPhoneNumber(form.getCellPhoneNumber());

        return seller;
    }
}