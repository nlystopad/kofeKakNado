package com.nss.kofekaknado.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coffee_points")
public class CoffeePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "point")
    private Set<Users> usersSet;

    @OneToMany(mappedBy = "point")
    private List<Event> events;
}
