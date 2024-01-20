package com.israel.coupon.cuponhub.service;

import com.israel.coupon.cuponhub.data.dto.CouponDto;
import com.israel.coupon.cuponhub.data.entity.Coupon;
import com.israel.coupon.cuponhub.data.repository.CouponRepository;
import com.israel.coupon.cuponhub.mapper.CouponMapper;
import com.israel.coupon.cuponhub.service.ex.CouponAlreadyPurchasedException;
import com.israel.coupon.cuponhub.service.ex.CouponExpiredException;
import com.israel.coupon.cuponhub.service.ex.NoSuchCouponException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper mapper;

    @Override
    public CouponDto getCoupon(UUID coupon) {
        Optional<Coupon> optCoupon = couponRepository.getCouponByUuid(coupon);

        if (optCoupon.isEmpty()) {
            throw new NoSuchCouponException("the coupon not found");
        }

        return mapper.toDto(optCoupon.get());
    }

    @Override
    public Set<CouponDto> getCustomerCoupons(UUID customer) {
        Set<Coupon> optCoupons = couponRepository.getCouponsByCustomersContaining(customer);

        return optCoupons.stream().map(mapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<CouponDto> getAllCompanyCoupons(UUID company) {
        Set<Coupon> companyCoupons = couponRepository.getCouponsByCompanyUuid(company);
        return companyCoupons.stream().map(mapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public CouponDto purchaseCouponByUuid(UUID customer, UUID coupon) {
        Optional<Coupon> optCoupon = couponRepository.getCouponByUuid(coupon);

        if (optCoupon.isEmpty()) {
            throw new NoSuchCouponException("the Coupon not found");
        }

        Coupon fetchedCoupon = optCoupon.get();

        if (fetchedCoupon.getEndDate().isBefore(LocalDateTime.now())) {
            throw new CouponExpiredException("This coupon is no longer available");
        }

        if (fetchedCoupon.getCustomers().stream().anyMatch(customer::equals)) {
            throw new CouponAlreadyPurchasedException("The fetchedCoupon has already been purchased by the customer. You can purchase only one Coupon");
        }

        fetchedCoupon.setAmount(fetchedCoupon.getAmount() - 1);
        fetchedCoupon.purchase(customer);

        return mapper.toDto(couponRepository.save(fetchedCoupon));
    }

    @Override
    public void removeCustomer(UUID customerUuid) {
        Set<Coupon> optCoupons = couponRepository.getCouponsByCustomersContaining(customerUuid);

        if (!optCoupons.isEmpty()) {
            optCoupons.forEach(coupon -> coupon.getCustomers().remove(customerUuid));
        }

        couponRepository.saveAll(optCoupons);
    }
}

