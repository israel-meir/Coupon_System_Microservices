package com.israel.coupon.cuponhub.service;

import com.israel.coupon.cuponhub.data.dto.CouponDto;

import java.util.Set;
import java.util.UUID;

public interface CouponService {
    CouponDto getCoupon(UUID coupon);

    Set<CouponDto> getCustomerCoupons(UUID CustomerUuid);

    Set<CouponDto> getAllCompanyCoupons(UUID companyUuid);

    CouponDto purchaseCouponByUuid(UUID CustomerUuid, UUID couponUuid);

    void removeCustomer(UUID customerUuid);
}
