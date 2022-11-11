package com.nss.kofekaknado.service;

import com.nss.kofekaknado.domain.Users;

import java.util.List;

public interface UserService {
    Users create(Users users);

    Users login(String phoneNumber);

    Users removeByPhoneNumber(String number);

    List<Users> getAll();
}
