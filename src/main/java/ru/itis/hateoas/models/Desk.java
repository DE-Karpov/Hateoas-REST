package ru.itis.hateoas.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    public void deReserve() {
        if (!isReserved) {
            throw new IllegalArgumentException();
        }
        isReserved = false;
    }
}
