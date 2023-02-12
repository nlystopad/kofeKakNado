package com.nss.kofekaknado.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Schema(description = "unique identifier of user", pattern = "sequence")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "User's phone number, used as login", example = "+380666168492", required = true, name = "Phone number")
    private String phoneNumber;

    @Column(nullable = false)
    @Schema(description = "Password, used by user to login", required = true)
    private String password;

    @Column(nullable = false)
    @Schema(description = "User's name", example = "Nikita", required = true)
    private String name;

    @Schema(description = "Amount of bonuses owned by user", example = "5")
    private Integer bonuses = 0;

    @Schema(description = "Technical field, which is used instead of deleting")
    @JsonIgnore
    private Boolean isDeleted = Boolean.FALSE;

    @Schema(description = "Technical field, which is used to let user use his bonuses")
    @JsonIgnore
    private Boolean patron = Boolean.FALSE;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Transient
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId().equals(users.getId()) && getPhoneNumber().equals(users.getPhoneNumber()) && getPassword().equals(users.getPassword()) && getName().equals(users.getName()) && getBonuses().equals(users.getBonuses()) && getIsDeleted().equals(users.getIsDeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhoneNumber(), getPassword(), getName(), getBonuses(), getIsDeleted());
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isDeleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }
}
