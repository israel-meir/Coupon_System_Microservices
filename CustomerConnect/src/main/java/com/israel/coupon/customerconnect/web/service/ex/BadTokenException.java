package com.israel.coupon.customerconnect.web.service.ex;

import org.springframework.web.client.RestClientException;

public class BadTokenException extends RuntimeException {
    public BadTokenException(RestClientException e) {
        super(e.getMessage());
    }
}
