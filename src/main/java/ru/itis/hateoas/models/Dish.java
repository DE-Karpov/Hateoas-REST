package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_generator")
    @SequenceGenerator(name = "dish_generator", sequenceName = "dish_seq")
    private Long id;
    private String name;
    private Long cost;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

}
