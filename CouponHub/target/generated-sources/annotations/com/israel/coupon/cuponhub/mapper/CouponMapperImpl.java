package com.israel.coupon.cuponhub.mapper;

import com.israel.coupon.cuponhub.data.dto.CouponDto;
import com.israel.coupon.cuponhub.data.entity.Coupon;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-24T09:20:39+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CouponMapperImpl implements CouponMapper {

    @Override
    public Coupon toEntity(CouponDto dto) {
        if ( dto == null ) {
            return null;
        }

        Coupon.CouponBuilder coupon = Coupon.builder();

        coupon.uuid( dto.getUuid() );
        coupon.companyUuid( dto.getCompanyUuid() );
        coupon.category( dto.getCategory() );
        coupon.title( dto.getTitle() );
        coupon.startDate( dto.getStartDate() );
        coupon.endDate( dto.getEndDate() );
        coupon.amount( dto.getAmount() );
        coupon.description( dto.getDescription() );
        coupon.price( dto.getPrice() );
        coupon.image( dto.getImage() );
        Set<UUID> set = dto.getCustomers();
        if ( set != null ) {
            coupon.customers( new LinkedHashSet<UUID>( set ) );
        }

        return coupon.build();
    }

    @Override
    public CouponDto toDto(Coupon entity) {
        if ( entity == null ) {
            return null;
        }

        CouponDto.CouponDtoBuilder couponDto = CouponDto.builder();

        couponDto.uuid( entity.getUuid() );
        couponDto.companyUuid( entity.getCompanyUuid() );
        couponDto.category( entity.getCategory() );
        couponDto.title( entity.getTitle() );
        couponDto.startDate( entity.getStartDate() );
        couponDto.endDate( entity.getEndDate() );
        couponDto.amount( entity.getAmount() );
        couponDto.description( entity.getDescription() );
        couponDto.price( entity.getPrice() );
        couponDto.image( entity.getImage() );
        Set<UUID> set = entity.getCustomers();
        if ( set != null ) {
            couponDto.customers( new LinkedHashSet<UUID>( set ) );
        }

        return couponDto.build();
    }
}
