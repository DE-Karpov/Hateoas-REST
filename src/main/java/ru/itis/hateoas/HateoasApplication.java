package ru.itis.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoas.models.*;
import ru.itis.hateoas.repositories.*;

import static java.util.Arrays.asList;
import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(HateoasApplication.class, args);

        CustomersRepository customersRepository = context.getBean(CustomersRepository.class);
        DishesRepository dishesRepository = context.getBean(DishesRepository.class);
        PlacesRepository placesRepository = context.getBean(PlacesRepository.class);
        DesksRepository desksRepository = context.getBean(DesksRepository.class);
        MenusRepository menusRepository = context.getBean(MenusRepository.class);

        Place beerHouse = Place.builder()
                .name("Beer House")
                .isFull(false)
                .build();

        Place chernovar = Place.builder()
                .name("Chernovar")
                .isFull(false)
                .build();

        Customer marsel = Customer.builder()
                .firstName("Марсель")
                .lastName("Сидиков")
                .place(beerHouse)
                .build();

        Customer salimov = Customer.builder()
                .firstName("Фарид")
                .lastName("Салимов")
                .place(chernovar)
                .build();

        Desk firstDeskInChernovar = Desk.builder()
                .number(1L)
                .place(chernovar)
                .isReserved(false)
                .build();

        Desk secondDeskInChernovar = Desk.builder()
                .number(2L)
                .place(chernovar)
                .isReserved(false)
                .build();

        Desk firstDeskInBeerHouse = Desk.builder()
                .number(1L)
                .place(beerHouse)
                .isReserved(false)
                .build();

        Desk secondDeskInBeerHouse = Desk.builder()
                .number(2L)
                .place(beerHouse)
                .isReserved(true)
                .build();

        Dish friedChicken = Dish.builder()
                .name("Fried chicken")
                .cost(300L)
                .build();

        Dish frenchFries = Dish.builder()
                .name("French fries")
                .cost(100L)
                .build();

        Dish beer = Dish.builder()
                .name("Beer")
                .cost(120L)
                .build();

        Menu chernovarMenu = Menu.builder()
                .dishes(asSet(frenchFries,friedChicken))
                .place(chernovar)
                .build();

        Menu beerHouseMenu = Menu.builder()
                .dishes(asSet(frenchFries,beer))
                .place(beerHouse)
                .build();

        placesRepository.saveAll(asList(beerHouse, chernovar));
        customersRepository.saveAll(asList(marsel, salimov));
        desksRepository.saveAll(asList(
                firstDeskInBeerHouse,
                secondDeskInBeerHouse,
                firstDeskInChernovar,
                secondDeskInChernovar));
        dishesRepository.saveAll(asList(friedChicken, frenchFries, beer));
        menusRepository.saveAll(asList(chernovarMenu,beerHouseMenu));
    }

}

