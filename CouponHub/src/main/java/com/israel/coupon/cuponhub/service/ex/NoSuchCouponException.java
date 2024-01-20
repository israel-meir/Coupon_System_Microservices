package com.israel.coupon.cuponhub.service.ex;

public class NoSuchCouponException extends RuntimeException {
    public NoSuchCouponException(String massage) {
        super(massage);
    }
}
