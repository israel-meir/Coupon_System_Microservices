package com.israel.coupon.cuponhub.data.repository;

import com.israel.coupon.cuponhub.data.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> getCouponByUuid(UUID uuid);

    Set<Coupon> getCouponsByCompanyUuid(UUID uuid);

    Set<Coupon> getCouponsByCustomersContaining(UUID uuid);


}
