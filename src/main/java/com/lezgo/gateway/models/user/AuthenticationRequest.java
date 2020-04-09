package com.lezgo.gateway.models.user;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}
