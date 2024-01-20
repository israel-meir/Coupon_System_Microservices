package com.israel.coupon.cuponhub.mapper;

import com.israel.coupon.cuponhub.data.dto.CouponDto;
import com.israel.coupon.cuponhub.data.entity.Coupon;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    Coupon toEntity(CouponDto dto);

    CouponDto toDto(Coupon entity);

}
