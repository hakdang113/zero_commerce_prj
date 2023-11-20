package com.commerce.zero_cms_security;

import com.commerce.zero_cms_security.common.UserType;
import com.commerce.zero_cms_security.common.UserVo;
import com.commerce.zero_cms_security.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Objects;

public class JwtAuthenticationTokenProvider {

    private final String secretKey = "secretKey";
    private final Long TOKEN_VALID_TIME = 1000L * 60 * 60 * 24; // 1일


    // Jwt 토큰 생성 메서드
    public String createToken(String userPk, Long id, UserType userType) {
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk)).setId(Aes256Util.encrypt(id.toString()));
        // userPk 암호화, id 암호화
        // userPk는 아직 무엇으로 설정 해야할 지 정하지 않은 상태
        // 암호화가 필요해서 일단 진행한 부분

        claims.put("roles", userType);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    // Jwt 토큰 유효성 검증 메서드
    public boolean isValidToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken); // 토큰 파싱
            return !claimsJws.getBody().getExpiration().before(new Date());
            // 토큰의 만료 시간이 현재 시간보다 이전인지 아닌지 만료 여부 체크
        } catch (Exception e) {
            return false;
        }
    }

    public UserVo getUserVo (String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return new UserVo(Long.valueOf(Objects.requireNonNull(
                Aes256Util.decrypt(claims.getId()))), Aes256Util.decrypt(claims.getSubject())
        );
    }

}