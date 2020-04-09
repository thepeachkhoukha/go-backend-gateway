package com.lezgo.gateway.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCircle {
    private int id;
    private String username;
    private String image;
}
