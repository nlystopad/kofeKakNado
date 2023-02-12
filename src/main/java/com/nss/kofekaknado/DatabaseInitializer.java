package com.nss.kofekaknado;

import com.nss.kofekaknado.model.domain.Role;
import com.nss.kofekaknado.model.domain.Users;
import com.nss.kofekaknado.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
@AllArgsConstructor
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Users admin = userRepository.findByPhoneNumber("admin");
        if (admin != null) {
            admin.setRoles(stringToRole(Set.of(Role.ADMIN)));
            userRepository.save(admin);
        } else {
            userRepository.save(new Users(1, "admin", passwordEncoder.encode("admin"), "admin", null, Boolean.FALSE, null, stringToRole(Set.of(Role.ADMIN))));
        }

    }

    protected Set<Role> stringToRole(Set<String> authorities) {
        if (authorities != null) {
            return authorities.stream().map(Role::new).collect(toSet());
        }
        return new HashSet<>();
    }
}
