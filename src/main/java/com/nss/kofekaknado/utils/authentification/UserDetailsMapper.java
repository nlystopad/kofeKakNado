package com.nss.kofekaknado.utils.authentification;

import com.nss.kofekaknado.domain.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {
    UserDetails toUserDetails(Users users) {
        return User.withUsername(users.getPhoneNumber())
                .password(users.getPassword())
                .roles(users.getRole())
                .build();
    }
}
