package com.nss.kofekaknado.model.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Role implements GrantedAuthority {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    @Override
    public String getAuthority() {
        return name;
    }

    public Role(String name) {
        this.name = name;
    }
}
