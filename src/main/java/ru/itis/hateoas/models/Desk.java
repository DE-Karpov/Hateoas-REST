package ru.itis.hateoas.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "place")
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    private Boolean isReserved;

    public void reserve() {
        if (isReserved) {
            throw new IllegalArgumentException();
        }
        isReserved = true;
    }
}
