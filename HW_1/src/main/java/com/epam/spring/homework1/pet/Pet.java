package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet {
  private final List<Animal> pets;

  public Pet(List<Animal> pets) {
    this.pets = pets;
  }

  public void printPets() {
    pets.forEach(pet -> System.out.println(pet.getClass().getSimpleName()));
  }
}
