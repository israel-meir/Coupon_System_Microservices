package com.israel.coupon.customerconnect.service.ex;

public class NoSuchCustomerException extends RuntimeException {
    public NoSuchCustomerException(String massage) {
        super(massage);
    }
}
