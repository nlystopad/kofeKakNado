package com.nss.kofekaknado.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;


    private Integer bonuses = 0;

    private Boolean isDeleted = Boolean.FALSE;

    private String role = "user";

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId().equals(users.getId()) && getPhoneNumber().equals(users.getPhoneNumber()) && getPassword().equals(users.getPassword()) && getName().equals(users.getName()) && getBonuses().equals(users.getBonuses()) && getIsDeleted().equals(users.getIsDeleted()) && getRole().equals(users.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhoneNumber(), getPassword(), getName(), getBonuses(), getIsDeleted(), getRole());
    }
}
