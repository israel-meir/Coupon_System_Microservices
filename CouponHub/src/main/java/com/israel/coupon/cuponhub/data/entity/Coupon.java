package com.israel.coupon.cuponhub.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcType(CharJdbcType.class)
    private UUID uuid;
    @JdbcType(CharJdbcType.class)
    private UUID companyUuid;
    private int category;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int amount;
    private String description;
    private BigDecimal price;
    private String image;
    @ElementCollection
    @CollectionTable(name = "coupon_purched_by", joinColumns = @JoinColumn(name = "coupon_id"))
    @JdbcType(CharJdbcType.class)
    private Set<UUID> customers = new HashSet<>();

    public void purchase(UUID customerUuid) {
        customers.add(customerUuid);
    }
}

