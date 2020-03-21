package ru.itis.hateoas.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "menus")
@ToString(exclude = "menus")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_generator")
    @SequenceGenerator(name = "dish_generator", sequenceName = "dish_seq")
    private Long id;

    @ManyToMany(mappedBy = "dishes")
    private Set<Menu> menus;

    private String name;
    private Long cost;

}