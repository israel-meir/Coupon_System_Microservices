package com.israel.coupon.customerconnect.service;

import com.israel.coupon.customerconnect.data.dto.CustomerDto;
import com.israel.coupon.customerconnect.web.dto.UpdateRequest;

import java.util.Set;
import java.util.UUID;

public interface CustomerService {
    CustomerDto createCustomer(UUID uuid, String name);

    CustomerDto getCustomerDetails(UUID uuid);

    Set<CustomerDto> getAllCustomersDetails(UUID uuid);

    CustomerDto updateCustomer(UUID uuid, UpdateRequest details);

    void deleteCustomer(UUID uuid);
}
