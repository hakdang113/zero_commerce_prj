package com.commerce.zero_cms_user_api.controller;

import com.commerce.zero_cms_user_api.service.SendEmailService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TestController {

    private final SendEmailService sendEmailService;

    @GetMapping
    public Response sendTestEmail() {
        return sendEmailService.sendEmail();
    }
}
