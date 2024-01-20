package com.israel.coupon.customerconnect.web.controller.advice;

import com.israel.coupon.customerconnect.service.ex.ExistingCustomerException;
import com.israel.coupon.customerconnect.service.ex.NoSuchCustomerException;
import com.israel.coupon.customerconnect.web.controller.CustomerController;
import com.israel.coupon.customerconnect.web.service.ex.BadTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(assignableTypes = CustomerController.class)
@ResponseBody
public class CustomerControllerAdvice {

    @ExceptionHandler(ExistingCustomerException.class)
    public ProblemDetail handleExistingCustomerException(ExistingCustomerException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    public ProblemDetail handleNoSuchCustomerException(NoSuchCustomerException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadTokenException.class)
    public ProblemDetail handleBadTokenException(BadTokenException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
}
