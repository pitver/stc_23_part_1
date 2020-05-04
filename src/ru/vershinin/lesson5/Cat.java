package ru.vershinin.lesson5;

/**
 * Cat extends Pet
 * @see Pet
 * @author Вершинин Пётр
 */
public class Cat extends Pet {
    public Cat( String nickname, Person owner, int weight) {
        super( nickname, owner, weight);
    }

    @Override
    public void addAnimals(Pet pets) {
        super.addAnimals(pets);
    }

    @Override
    public void searchByNickname(String nickName) {
        super.searchByNickname(nickName);
    }

    @Override
    public void print() {
        super.print();
    }


}
