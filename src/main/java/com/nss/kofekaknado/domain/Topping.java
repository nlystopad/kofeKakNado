package com.nss.kofekaknado.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topping")
public class Topping {  // todo: add  swagger

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Schema(description = "unique identifier of topping", pattern = "sequence")
    private Integer id;

    @Schema(description = "name of topping", required = true, example = "chocolate")
    @Column(nullable = false)
    private String name;

    @Schema(description = "price of topping", required = true, example = "5")
    @Column(nullable = false)
    private Integer price;


    @OneToOne(mappedBy = "topping")
    private BuffCoffee buffCoffee;

}
