package com.israel.coupon.cuponhub.web.controller;

import com.israel.coupon.cuponhub.data.dto.CouponDto;
import com.israel.coupon.cuponhub.service.CouponService;
import com.israel.coupon.cuponhub.web.dto.UserDto;
import com.israel.coupon.cuponhub.web.service.UserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final UserVerification userVerification;
    private final CouponService couponService;

    @GetMapping("/{couponUuid}")
    public ResponseEntity<CouponDto> getCouponByUuid(@PathVariable UUID couponUuid, @RequestHeader("Authorization") String token) {
        userVerification.byToken(token);
        return ResponseEntity.ok(couponService.getCoupon(couponUuid));
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<CouponDto>> getAllCustomerCoupons(@RequestHeader("Authorization") String token) {
        UserDto userDetails = userVerification.byToken(token);
        return ResponseEntity.ok(couponService.getCustomerCoupons(userDetails.getUuid()));
    }

    @GetMapping("/company")
    public ResponseEntity<Set<CouponDto>> getAllCompanyCoupons(@RequestHeader("Authorization") String token) {
        UserDto userDetails = userVerification.byToken(token);
        return ResponseEntity.ok(couponService.getAllCompanyCoupons(userDetails.getUuid()));

    }

    @GetMapping("/purchase/{couponUuid}")
    public ResponseEntity<CouponDto> purchaseCoupon(@PathVariable UUID couponUuid, @RequestHeader("Authorization") String token) {
        UserDto userDetails = userVerification.byToken(token);
        return ResponseEntity.ok(couponService.purchaseCouponByUuid(userDetails.getUuid(), couponUuid));
    }

    @DeleteMapping("/deleteCustomer/{customerUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCustomerFromCoupons(@PathVariable UUID customerUuid) {
        couponService.removeCustomer(customerUuid);
    }

}
