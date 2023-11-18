package com.commerce.zero_cms_order_api.domain.model;


// @AuditOverride(forClass = BaseEntity.class) // BaseEntity 클래스 내의 속성들을 사용하기 위한 어노테이션
// extends 를 이용하여 아래의 속성들을 여러 엔티티에서 사용할 수 있도록 함

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdDate; // 만들어진 날짜
    @LastModifiedDate
    private LocalDateTime modifiedDate; // 수정된 날짜
}
