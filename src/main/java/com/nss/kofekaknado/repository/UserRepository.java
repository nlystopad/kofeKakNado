package com.nss.kofekaknado.repository;

import com.nss.kofekaknado.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByPhoneNumber(String phoneNumber);


}
