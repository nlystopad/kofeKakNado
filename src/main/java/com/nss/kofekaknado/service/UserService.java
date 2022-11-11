package com.nss.kofekaknado.service;

import com.nss.kofekaknado.domain.Users;

import java.util.List;

public interface UserService {
    void create(Users users);

    Users login(String phone, String password);

    void removeByPhoneNumber(String number);

    List<Users> getAll();
}
