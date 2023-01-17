package com.nss.kofekaknado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {

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
    @Schema(description = "User's name", example = "zxcursed", required = true)
    private String name;

    @Schema(description = "Amount of bonuses owned by user", example = "5")
    private Integer bonuses = 0;

    @Schema(description = "Technical field, which is used instead of deleting")
    private Boolean isDeleted = Boolean.FALSE;

    @Schema(description = "User's role", example = "user", allowableValues = {"user", "admin"})
    private String role = "user";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Schema(description = "List of user's preorders")
    @JsonIgnore
    private List<Preorder> preorders;


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
