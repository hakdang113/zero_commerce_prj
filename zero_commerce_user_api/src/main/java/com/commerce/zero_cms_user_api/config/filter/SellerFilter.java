package com.commerce.zero_cms_user_api.config.filter;

import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import com.commerce.zero_cms_security.common.UserVo;
import com.commerce.zero_cms_user_api.service.seller.SellerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 토큰을 사용하기 위한 필터
@WebFilter(urlPatterns = "/customer/*") // customer로 들어가는 모든 패턴에 대해 필터를 적용
@RequiredArgsConstructor
public class SellerFilter implements Filter {

    private final JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;
    private final SellerService sellerService;


    // Filter를 implements 하면 doFilter를 Override 해주어야 함
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("X-AUTH_TOKEN"); // "X-AUTH_TOKEN" 헤더로 jwt 토큰을 받음
        if (!jwtAuthenticationTokenProvider.isValidToken(token)) { // isValidToken() 으로 token이 유효한 토큰인지 확인
            throw new ServletException("Invalid Access");
        }

        // 유효한 토큰이라면
        UserVo userVo = jwtAuthenticationTokenProvider.getUserVo(token);
        sellerService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
                .orElseThrow(
                        () -> new ServletException("Invalid Access")
                );

        chain.doFilter(request, response);
    }
}