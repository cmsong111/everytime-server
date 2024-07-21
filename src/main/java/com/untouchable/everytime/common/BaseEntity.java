package com.untouchable.everytime.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

/**
 * Base Entity
 * 모든 Entity의 상위 클래스
 *
 * @author cmsong111
 */
@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@SoftDelete(columnName = "is_deleted")
public abstract class BaseEntity {
    /**
     * 생성일
     * 생성 시 자동으로 값이 설정됨
     */
    @CreatedDate
    @Column(updatable = false)
    private Timestamp createdAt;

    /**
     * 수정일
     * 수정 시 자동으로 값이 갱신됨
     */
    @LastModifiedDate
    private Timestamp updatedAt;
}
