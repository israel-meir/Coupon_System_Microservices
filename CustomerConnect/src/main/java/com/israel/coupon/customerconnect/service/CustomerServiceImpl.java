package com.israel.coupon.customerconnect.service;

import com.israel.coupon.customerconnect.mappar.CustomerMapper;
import com.israel.coupon.customerconnect.service.ex.ExistingCustomerException;
import com.israel.coupon.customerconnect.data.dto.CustomerDto;
import com.israel.coupon.customerconnect.data.entity.Customer;
import com.israel.coupon.customerconnect.data.repository.CustomerRepository;
import com.israel.coupon.customerconnect.service.ex.NoSuchCustomerException;
import com.israel.coupon.customerconnect.web.dto.UpdateRequest;
import com.israel.coupon.customerconnect.web.service.UserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    private final UserVerification userVerification;

    @Override
    public CustomerDto createCustomer(UUID customerUuid, String name) {
        Optional<Customer> optCustomer = customerRepository.getCustomerByUuid(customerUuid);

        if (optCustomer.isEmpty()) {

            Customer customer = new Customer();
            customer.setUuid(customerUuid);
            customer.setFirstName(name);
            return mapper.toDto(customerRepository.save(customer));

        }

        throw new ExistingCustomerException("the customer already exists");
    }


    @Override
    public CustomerDto getCustomerDetails(UUID uuid) {
        Optional<Customer> optCustomer = customerRepository.getCustomerByUuid(uuid);

        if (optCustomer.isEmpty()) {
            throw new NoSuchCustomerException("the customer not found");
        }

        Customer customer = optCustomer.get();
        return mapper.toDto(customer);
    }

    @Override
    // FIXME: 17/12/2023 רק חברה תוכל לגשת למידע הזה ולא לקוח יחיד (גם זה יותנה בהרשאה של החברה למשל אם כל הלקוחות הם של הקופונים שלה
    public Set<CustomerDto> getAllCustomersDetails(UUID customerUuid) {
        // FIXME: 24/12/2023בשלב הזה יש לברר מי הם לקוחות החברה ולהחזיר רק את הלקוחות שקשורים אליה, כרגע מסד הנתונים לא מכיל מידע שמאפשר זאת

        List<Customer> optCustomers = customerRepository.findAll();

        return optCustomers.stream()
                .map(mapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CustomerDto updateCustomer(UUID customerUuid, UpdateRequest details) {
        Optional<Customer> optCustomer = customerRepository.getCustomerByUuid(customerUuid);

        if (optCustomer.isEmpty()) {
            throw new NoSuchCustomerException("the customer not found");
        }

        Customer customer = optCustomer.get();
        customer.setFirstName(details.getFirstName());
        customer.setLastName(details.getLastName());

        return mapper.toDto(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(UUID uuid) {
        Optional<Customer> optCustomer = customerRepository.getCustomerByUuid(uuid);

        if (optCustomer.isEmpty()) {
            throw new NoSuchCustomerException("the customer not found");
        }

        userVerification.deleteCustomer(uuid);

        customerRepository.delete(optCustomer.get());
    }
}
