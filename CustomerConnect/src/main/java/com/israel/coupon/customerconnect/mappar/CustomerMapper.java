package com.israel.coupon.customerconnect.mappar;

import com.israel.coupon.customerconnect.data.dto.CustomerDto;
import com.israel.coupon.customerconnect.data.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerDto dto);

    CustomerDto toDto(Customer Entity);
}
