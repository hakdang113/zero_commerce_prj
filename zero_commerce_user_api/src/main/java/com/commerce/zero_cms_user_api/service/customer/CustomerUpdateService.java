package com.commerce.zero_cms_user_api.service.customer;


import com.commerce.zero_cms_user_api.domain.form.UpdateForm;
import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.domain.repository.CustomerRepository;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerUpdateService {

    private final CustomerRepository customerRepository;


    @Transactional
    public CustomerEntity updateCustomer(String email, UpdateForm form) {
        CustomerEntity customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER));

        customer.setUserPassword(form.getUserPassword());
        customer.setAddress(form.getAddress());
        customer.setCellPhoneNumber(form.getCellPhoneNumber());

        return customer;
    }
}
