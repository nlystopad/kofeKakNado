package com.nss.kofekaknado.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coffee")
public class Coffee {  // todo: add  swagger

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Schema(description = "unique identifier of coffee", pattern = "sequence")
    private Integer id;

    @Schema(description = "name of coffee", required = true, example = "latte")
    @Column(nullable = false)
    private String name;

    @Schema(description = "price of coffee", required = true, example = "50")
    @Column(nullable = false)
    private Integer price;

    @OneToOne(mappedBy = "coffee")
    private BuffCoffee buffCoffee;

}
