package com.israel.coupon.customerconnect.data.dto;



import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String Email;
    private Long version;
}
