package com.nss.kofekaknado.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plan_events")
public class PlanEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "event")
    List<Event> events;

    @OneToMany(mappedBy = "event")
    List<CoffeePoint> points;

}
