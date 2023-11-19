package com.commerce.zero_cms_user_api.domain.repository;

import com.commerce.zero_cms_user_api.domain.model.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {

    Optional<SellerEntity> findByIdAndEmail(Long id, String email);

    Optional<SellerEntity> findByEmailAndUserPasswordAndVerifyIsTrue(String email, String password);

    Optional<SellerEntity> findByEmail(String email);
}
