package com.israel.coupon.customerconnect.mappar;

import com.israel.coupon.customerconnect.data.dto.CustomerDto;
import com.israel.coupon.customerconnect.data.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-24T09:20:45+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( dto.getId() );
        customer.uuid( dto.getUuid() );
        customer.firstName( dto.getFirstName() );
        customer.lastName( dto.getLastName() );
        customer.version( dto.getVersion() );

        return customer.build();
    }

    @Override
    public CustomerDto toDto(Customer Entity) {
        if ( Entity == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.id( Entity.getId() );
        customerDto.uuid( Entity.getUuid() );
        customerDto.firstName( Entity.getFirstName() );
        customerDto.lastName( Entity.getLastName() );
        customerDto.version( Entity.getVersion() );

        return customerDto.build();
    }
}
