package ru.vershinin.lesson11;

/**
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {

    public static void main(String[] args) {
        Person mike = new Person("Mike", 25, Gender.MALE);
        Person nike = new Person("MNike", 18, Gender.FEMALE);
        ActionsWithPets pet = new ActionsWithPets();


        pet.addAnimals(new Cat("Jim", mike, 4));
        pet.addAnimals(new Cat("Jim", mike, 4));
        pet.addAnimals(new Cat("Jira", mike, 3));
        pet.addAnimals(new Cat("Ben", mike, 5));
        pet.addAnimals(new Cat("Tam", nike, 1));
        pet.addAnimals(new Cat("Ad", mike, 4));
        pet.addAnimals(new Dog("Tomi", nike, 9));
        pet.addAnimals(new Dog("Remy", mike, 2));
        pet.addAnimals(new Dog("Remy", nike, 1));


        pet.print();
        pet.searchByNickname("Tam");
        pet.updatePet(5, "Jimiiiiii", 14);
        pet.print();


    }
}
