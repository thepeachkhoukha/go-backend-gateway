package com.lezgo.gateway.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FriendshipRequestWrapper {
    private String username;
    private String friendname;
    private String token;
}
