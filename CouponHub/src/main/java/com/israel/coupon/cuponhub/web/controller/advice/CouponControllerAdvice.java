package com.israel.coupon.cuponhub.web.controller.advice;

import com.israel.coupon.cuponhub.service.ex.CouponAlreadyPurchasedException;
import com.israel.coupon.cuponhub.service.ex.CouponExpiredException;
import com.israel.coupon.cuponhub.service.ex.NoSuchCouponException;
import com.israel.coupon.cuponhub.web.controller.CouponController;
import com.israel.coupon.cuponhub.web.service.ex.BadTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(assignableTypes = CouponController.class)
@ResponseBody
public class CouponControllerAdvice {

    @ExceptionHandler(NoSuchCouponException.class)
    public ProblemDetail handleNoSuchCouponException(NoSuchCouponException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadTokenException.class)
    public ProblemDetail handBadTokenException(BadTokenException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(CouponAlreadyPurchasedException.class)
    public ProblemDetail handCouponAlreadyPurchasedException(CouponAlreadyPurchasedException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(CouponExpiredException.class)
    public ProblemDetail handCouponExpiredException(CouponExpiredException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
    }
}
