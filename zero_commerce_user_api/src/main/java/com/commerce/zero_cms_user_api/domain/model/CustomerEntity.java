package com.commerce.zero_cms_user_api.domain.model;

import com.commerce.zero_cms_user_api.domain.form.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity(name = "customer")
//// 두 개 이상의 컬럼에 유니크 설정
//@Table (name = "user", uniqueConstraints = {
//        @UniqueConstraint(
//                name = "userUniqueConstraints",
//                columnNames = {"USER_ID", "USER_EMAIL"}
//        )
//})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AuditOverride(forClass = BaseEntity.class) // BaseEntity 클래스 내의 속성들을 사용하기 위한 어노테이션
// CustomerEntity 클래스가 업데이트 될 때마다 자동으로 createTime 부분이랑 modifiedTime 부분이 변경됨

public class CustomerEntity extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 숫자값이 순차적으로 올라가도록 설정
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String userPassword;
    private String cellPhoneNumber;
    private String address;
    private LocalDate birthDate;


    // 이메일 인증에 필요한 필드
    private LocalDateTime verifyExpiredTime; // 인증 유효 시간
    private String verificationCode; // 인증 코드
    private boolean verify; // 인증 여부


    // SignUpForm 을 통해서 받음
    // CustomerEntity 객체를 반환
    public static CustomerEntity from(SignUpForm form) {
        return CustomerEntity.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT)) // unique 체크를 위해 LowerCase로 정렬
                .userPassword(form.getUserPassword())
                .name(form.getName())
                .cellPhoneNumber(form.getCellPhoneNumber())
                .address(form.getAddress())
                .birthDate(form.getBirthDate())
                .build();
    }

}
