package com.israel.coupon.cuponhub.service.ex;

public class CouponAlreadyPurchasedException extends RuntimeException {
    public CouponAlreadyPurchasedException(String massage) {
        super(massage);
    }
}
