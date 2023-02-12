package com.nss.kofekaknado.service;

import com.nss.kofekaknado.model.domain.Users;
import com.nss.kofekaknado.repository.UserRepository;
import com.nss.kofekaknado.utils.exception.AlreadyExistException;
import com.nss.kofekaknado.utils.exception.UserNotFoundException;
import com.nss.kofekaknado.utils.exception.WrongPasswordException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Users create(Users users) {
        checkExist(users);
        String password = users.getPassword();
        if (password == null ) {
            throw new WrongPasswordException("Sorry, you MUST HAVE a password");
        } else if (password.equals("")) {
            throw new WrongPasswordException("Password field should not be empty");
        }
        users.setPassword(passwordEncoder.encode(password));
        return userRepository.save(users);
    }


    @Override
    public Users removeByPhoneNumber(String number) {
        log.info("removeByPhoneNumber() - start: number = {}", number);
        Users u = getUserByPhoneNumber(number);
        u.setIsDeleted(Boolean.TRUE);
        Users deleted = userRepository.save(u);
        log.info("removeByPhoneNumber() - end: number = {}", number);
        return deleted;
    }

    @Override
    public Users addBonuses(String number, Integer bonusesAmount) {
        log.info("addBonuses() - start: bonusesAmount = {}", bonusesAmount);
        Users user = getUserByPhoneNumber(number);
        user.setBonuses(user.getBonuses() + bonusesAmount);
        if (user.getBonuses() >= 5000 && !user.getPatron()) {
            user.setPatron(true);
        }
        log.info("addBonuses() - bonuses: before = {}", user.getBonuses());
        Users saved = userRepository.save(user);
        log.info("addBonuses() - end: finalAmount = {}", saved.getBonuses());
        return saved;
    }

    @Override
    public Users removeBonuses(String number, Integer bonusesAmount) {
        log.info("addBonuses() - start: bonusesAmount = {}", bonusesAmount);
        Users user = getUserByPhoneNumber(number);
        user.setBonuses(user.getBonuses() - bonusesAmount);
        log.info("addBonuses() - bonuses: before = {}", user.getBonuses());
        Users saved = userRepository.save(user);
        log.info("addBonuses() - end: finalAmount = {}", saved.getBonuses());
        return saved;
    }

    private Users getUserByPhoneNumber(String number) {
        Users u = userRepository.findByPhoneNumber(number);
        if (u == null) {
            throw new UserNotFoundException(String.format("User with phone number %s was not found", number));
        }
        return u;
    }

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    /**
     * Technical method - this method checks if this user already exist in database
     *
     * @param users - user to be checked
     */
    private void checkExist(Users users) {
        Users byPhone = userRepository.findByPhoneNumber(users.getPhoneNumber());
        if (byPhone != null) {
            throw new AlreadyExistException();
        }
    }

}


