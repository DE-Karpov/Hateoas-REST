package ru.itis.hateoas.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"customer", "desks", "menu"})
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_generator")
    @SequenceGenerator(name = "place_generator", sequenceName = "place_seq")
    private Long id;

    private String name;
    private Boolean isFull;

    @OneToMany(mappedBy = "place")
    private List<Dish> menu;

    @OneToMany(mappedBy = "place")
    private List<Desk> desks;

    @OneToMany(mappedBy = "place")
    private List<Customer> customer;

    public boolean isFull() {
        for (Desk desk : desks) {
            if (!desk.getIsReserved())
                return this.isFull = false;
        }
        return this.isFull = true;
    }
}
