# Customer Registration and Coupon Purchase Application

## Overview

This application enables customers to register in a dedicated database, facilitating the purchase of coupons. Registered customers can later log in using their username and password. The system verifies the client's identity and returns a token, granting access to the application's endpoints. Note that the verification module was developed by my lecturer.

## Microservices Architecture

The application is built on a microservices architecture, fostering seamless communication among various components. The microservices include:

1. **authforge**: Responsible for user registration, login verification, and token issuance.
2. **archimedes**: Uses Eureka Netflix for improved internal communication between services.
3. **gurdway**: Contains the gateway, providing convenient and uniform client access to all application services.
4. **couponhub**: Manages the coupon table, handling operations such as addition, purchase, deletion, and tracking customers who purchased the coupon.
5. **customerConnect**: Manages the customer table, allowing for the reception and management of customer information.

## Technologies Used

The application leverages the following technologies:

- **Java**: The primary programming language for development.
- **Spring Boot**: A framework for building Java-based microservices.
- **MySQL**: The relational database management system used for data storage.

## Microservices Responsibilities

- **authforge**: User registration, login verification, and token issuance.
- **archimedes**: Internal communication facilitation through Eureka Netflix.
- **gurdway**: Gateway for unified client access to all application services.
- **couponhub**: Management of the coupon table, including addition, purchase, deletion, and customer tracking.
- **customerConnect**: Management of the customer table, handling customer information.
