package com.WPsports.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {
    @CreatedDate
    private LocalDate createAt;

    @LastModifiedDate
    private LocalDate modifiedAt;
}
