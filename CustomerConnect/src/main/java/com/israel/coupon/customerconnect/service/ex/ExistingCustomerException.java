package com.israel.coupon.customerconnect.service.ex;

public class ExistingCustomerException extends RuntimeException {
    public ExistingCustomerException(String massage) {
        super(massage);
    }
}
