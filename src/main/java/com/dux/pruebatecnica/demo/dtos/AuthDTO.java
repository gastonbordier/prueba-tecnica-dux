package com.dux.pruebatecnica.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
public class AuthDTO {
        private String username;
        private String password;
}
