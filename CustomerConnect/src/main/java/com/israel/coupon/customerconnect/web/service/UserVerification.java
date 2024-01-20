package com.israel.coupon.customerconnect.web.service;

import com.israel.coupon.customerconnect.web.dto.UserDto;
import com.israel.coupon.customerconnect.web.service.ex.BadTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UserVerification {
    private final RestTemplate restTemplate;
    private final String authForgeApiUrl;
    private final String couponHubUriForDeleteCustomer;

    public UserVerification(RestTemplate restTemplate, @Value("${auth.api.url}") String authForgeApiUrl, @Value("${coupon.api.delete.url}") String couponHubUriForDeleteCustomer) {
        this.restTemplate = restTemplate;
        this.authForgeApiUrl = authForgeApiUrl;
        this.couponHubUriForDeleteCustomer = couponHubUriForDeleteCustomer;
    }

    public UserDto byToken(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);

            ResponseEntity<UserDto> user = restTemplate.exchange(authForgeApiUrl, HttpMethod.GET, new HttpEntity<>(headers), UserDto.class);
            return user.getBody();
        } catch (RestClientException e) {
            throw new BadTokenException(e);
        }
    }


    public void deleteCustomer(UUID customerUuid) {
        try {
//              String url = couponHubUriForDeleteCustomer + customerUuid;
            restTemplate.delete(couponHubUriForDeleteCustomer + customerUuid);
        } catch (RestClientException e) {
            throw new BadTokenException(e);
        }
    }
}


