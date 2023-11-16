package com.commerce.zero_cms_user_api.domain.repository;

import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Entity 모델들을 통해 데이터를 주고 받기 위한 Repository 인터페이스
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


}
