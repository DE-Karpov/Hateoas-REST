package ru.itis.hateoas.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"customer", "desks"})
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean isFull;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Menu menu;

    @OneToMany(mappedBy = "place")
    private List<Desk> desks;

    @OneToMany(mappedBy = "place")
    private List<Customer> customer;

    public void isFull() {
        for (Desk desk : desks) {
            if (!desk.getIsReserved()) {
                this.isFull = false;
                return;
            }
        }
        this.isFull = true;
    }
}
