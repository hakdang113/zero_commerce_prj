package com.commerce.zero_cms_user_api.service;

import com.commerce.zero_cms_user_api.client.MailgunClient;
import com.commerce.zero_cms_user_api.client.mailgun.SendMailForm;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailService {

    private final MailgunClient mailgunClient;

    public Response sendEmail() {

        SendMailForm form = SendMailForm.builder()
                .from("zerobaseTest@my.com")
                .to("Insert Your Email")
                .subject("Test email from zerobase-test")
                .text("my text")
                .build();

        return mailgunClient.sendEmail(form);
    }
}
