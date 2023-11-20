package com.commerce.zero_cms_user_api.client;

import com.commerce.zero_cms_user_api.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;


// 이메일 사용을 위한 인터페이스
@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun") // 여러 개의 FeignClient를 사용할 것이므로
public interface MailgunClient {


    // 메일을 보내는 부분
    @PostMapping("Insert_Your_Mailgun_Domain_Name/messages") // Mailgun 도메인명 기입
    Response sendEmail(@SpringQueryMap SendMailForm form);
    // queryString 형태로 데이터를 보내기 때문에 @SpringQueryMap 사용

}