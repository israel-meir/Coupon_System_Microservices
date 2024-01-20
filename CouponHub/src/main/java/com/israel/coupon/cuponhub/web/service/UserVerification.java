package com.israel.coupon.cuponhub.web.service;

import com.israel.coupon.cuponhub.web.dto.UserDto;
import com.israel.coupon.cuponhub.web.service.ex.BadTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserVerification {
    private final RestTemplate restTemplate;
    private final String authForgeApiUrl;

    public UserVerification(RestTemplate restTemplate, @Value("${auth.api.url}") String authForgeApiUrl) {
        this.restTemplate = restTemplate;
        this.authForgeApiUrl = authForgeApiUrl;
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

}


