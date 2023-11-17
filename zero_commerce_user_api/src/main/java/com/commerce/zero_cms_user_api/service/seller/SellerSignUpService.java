package com.commerce.zero_cms_user_api.service.seller;

import com.commerce.zero_cms_user_api.domain.form.SignUpForm;
import com.commerce.zero_cms_user_api.domain.model.SellerEntity;
import com.commerce.zero_cms_user_api.domain.repository.SellerRepository;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SellerSignUpService {

    private final SellerRepository sellerRepository;

    // 판매자 회원 가입
    public SellerEntity sellerSignUp(SignUpForm form) {
        return sellerRepository.save(SellerEntity.from(form));
    }

    // 판매자 이메일 중복을 확인하기 위한 메서드
    public boolean isEmailExist(String email) {
        return sellerRepository.findByEmail(email).isPresent();
    }

    // 판매자 이메일 인증 진행 메서드
    @Transactional
    public void verifyEmail(String email, String code) {
        SellerEntity seller = sellerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER));

        if (seller.isVerify()) { // 이미 인증된 상태
            throw new CustomException(CustomErrorCode.ALREADY_VERIFIED);
        } else if (!seller.getVerificationCode().equals(code)) { // 인증 코드 오류
            throw new CustomException(CustomErrorCode.INCORRECT_CODE);
        } else if (seller.getVerifyExpiredTime().isBefore(LocalDateTime.now())) { // 인증 시간 만료
            throw new CustomException(CustomErrorCode.VALID_VERIFICATION_TIME_OVER);
        }

        seller.setVerify(true);
    }

    @Transactional
    public LocalDateTime changeSellerValidationEmail(Long sellerId, String verificationCode) {
        Optional<SellerEntity> sellerEntityOptional = sellerRepository.findById(sellerId);

        if (sellerEntityOptional.isPresent()) {
            SellerEntity seller = sellerEntityOptional.get();
            seller.setVerificationCode(verificationCode);
            seller.setVerifyExpiredTime(LocalDateTime.now().plusDays(1));
            return seller.getVerifyExpiredTime();
        }
        throw new CustomException(CustomErrorCode.NOT_FOUND_USER);
    }
}
