package com.israel.coupon.customerconnect.data.repository;

import com.israel.coupon.customerconnect.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> getCustomerByUuid(UUID uuid);


}
