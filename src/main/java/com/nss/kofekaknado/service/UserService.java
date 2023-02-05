package com.nss.kofekaknado.service;

import com.nss.kofekaknado.model.domain.Users;

import java.util.List;

public interface UserService {
    Users create(Users users);

    Users removeByPhoneNumber(String number);

    List<Users> getAll();
}
