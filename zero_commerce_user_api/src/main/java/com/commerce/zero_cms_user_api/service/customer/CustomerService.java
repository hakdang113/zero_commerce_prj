package com.commerce.zero_cms_user_api.service.customer;


import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 이메일, 패스워드를 받아 인증이 완료된 고객 조회
    public Optional<CustomerEntity> findValidCustomer(String email, String password) {
        return customerRepository.findByEmail(email).stream()
                .filter(
                        customerEntity -> customerEntity.getUserPassword().equals(password) && customerEntity.isVerify()
                )
                .findFirst();
        // customerRepository에서 email을 기준으로 하나를 가져옴
        // 고객이 입력한 password와 가입했던 getPassWord()와 같고,
        // 이메일이 인증된 고객이라면
    }


    public Optional<CustomerEntity> findByIdAndEmail(Long id, String email) {
        return customerRepository.findById(id).stream()
                .filter(
                        customerEntity -> customerEntity.getEmail().equals(email)
                )
                .findFirst();
        // customerRepository에서 id 와 email을 기준으로 하나를 가져옴
        // 고객이 입력한 id, email과 가입했던 getId, getEmail이 같으면
    }

}
