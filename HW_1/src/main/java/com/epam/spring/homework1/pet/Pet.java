package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Pet  {
    List<Animal> pets;

    public Pet(List<Animal> pets) {
        this.pets = pets;
    }

    public void printPets(){
        for (Animal animal: pets) {
            System.out.println(animal.getClass().getSimpleName());
        }
    }
}
