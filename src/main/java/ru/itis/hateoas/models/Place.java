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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Menu menu;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Desk> desks;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
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

    public void addDesk(Desk desk){
        desk.setPlace(this);
        this.getDesks().add(desk);
    }

    public void removeDesk(Desk desk){
        this.getDesks().remove(desk);
        desk.setPlace(null);
    }
}
