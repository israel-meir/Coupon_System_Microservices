package com.israel.coupon.cuponhub.service.ex;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException(String massage) {
        super(massage);
    }
}
