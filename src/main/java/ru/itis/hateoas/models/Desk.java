package ru.itis.hateoas.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "place")
@Table(name = "Desk")
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_generator")
    @SequenceGenerator(name = "table_generator", sequenceName = "table_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    private Boolean isReserved;

    public void reserve() {
        isReserved = true;
    }
}
