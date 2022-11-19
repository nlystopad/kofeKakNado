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
@Table(name = "buffed_coffee")
public class BuffCoffee {  // todo: add  swagger
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Schema(description = "unique identifier of buffed coffee", pattern = "sequence")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "coffee_id", referencedColumnName = "id")
    private Coffee coffee;

    @OneToOne
    @JoinColumn(name = "topping_id", referencedColumnName = "id")
    private Topping topping;

    @ManyToOne
    @JoinColumn(name = "preorder_id", referencedColumnName = "id")
    private Preorder preorder;
}
