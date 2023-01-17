package com.nss.kofekaknado.domain;

import com.nss.kofekaknado.utils.enums.OrderStatuses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "preorders")
public class Preorder {  // todo: add  swagger
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatuses status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @OneToMany(mappedBy = "preorder")
    private List<MenuItem> items;

    @OneToOne
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    private CoffeePoint point;
}
