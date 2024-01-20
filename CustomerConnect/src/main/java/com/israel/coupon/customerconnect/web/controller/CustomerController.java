package com.israel.coupon.customerconnect.web.controller;

import com.israel.coupon.customerconnect.data.dto.CustomerDto;
import com.israel.coupon.customerconnect.service.CustomerService;
import com.israel.coupon.customerconnect.web.dto.UpdateRequest;
import com.israel.coupon.customerconnect.web.dto.UserDto;
import com.israel.coupon.customerconnect.web.service.UserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final UserVerification userVerification;

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestHeader("Authorization") String token) {
        UserDto userDetails = userVerification.byToken(token);
        return ResponseEntity.ok(customerService.createCustomer(userDetails.getUuid(), userDetails.getUsername()));
    }

    @GetMapping("/customers/details")
    public ResponseEntity<CustomerDto> getCustomerDetails(@RequestHeader("Authorization") String token) {
        UserDto userDetails = userVerification.byToken(token);
        return ResponseEntity.ok(customerService.getCustomerDetails(userDetails.getUuid()));
    }


    @GetMapping("/customers")
    public ResponseEntity<Set<CustomerDto>> getAllCustomerDetails(@RequestHeader("Authorization") String token) {
        UserDto userDetails = userVerification.byToken(token);
        return ResponseEntity.ok(customerService.getAllCustomersDetails(userDetails.getUuid()));
    }

    @PutMapping("/customers")
    public ResponseEntity<CustomerDto> updateCustomerDetails(@RequestHeader("Authorization") String token, @RequestBody UpdateRequest details) {
        UserDto userDetails = userVerification.byToken(token);
        return ResponseEntity.ok(customerService.updateCustomer(userDetails.getUuid(), details));

    }

    @DeleteMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader("Authorization") String token) {
        UserDto userDetails = userVerification.byToken(token);
        customerService.deleteCustomer(userDetails.getUuid());
    }
}
