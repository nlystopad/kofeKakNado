package com.nss.kofekaknado.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem {  // todo: add  swagger

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Schema(description = "unique identifier of menu item", pattern = "sequence")
    private Integer id;

    @Schema(description = "name of menu item", required = true, example = "latte")
    @Column(nullable = false)
    private String name;

    @Schema(description = "price of menu item", required = true, example = "50.00")
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "preorder_id", referencedColumnName = "id")
    private Preorder preorder;


}
