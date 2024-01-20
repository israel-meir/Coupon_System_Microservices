package com.israel.coupon.cuponhub.data.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CouponDto {
    private UUID uuid;
    private UUID companyUuid;
    private int category;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int amount;
    private String description;
    private BigDecimal price;
    private String image;
    private Set<UUID> customers;
}
