package ru.vershinin.lesson11;
import java.util.*;

/**
 * картотека домашних животных
 * Animal
 *
 * @author Вершинин Пётр
 */
public class Pet implements Comparable<Pet> {

    private static int count = 1;
    private int id;
    protected String nickname;
    protected Person owner;
    protected Integer weight;
    /**
     * пустой конструктор объекта Pet
     */
    public Pet() {
    }
    /**
     * Конструтор объекта Pet
     *
     * @param nickname - кличка животного, String
     * @param owner    - хозяин животного
     * @param weight   - вес животного, Integer
     * @see Person
     */
    public Pet(String nickname, Person owner, int weight) {
        id = count++;
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }
    @Override
    public boolean equals(Object equalsPet) {
        if (this == equalsPet) return true;
        if (equalsPet == null || getClass() != equalsPet.getClass()) return false;
        Pet pet = (Pet) equalsPet;
        return Objects.equals(nickname, pet.nickname) &&
                Objects.equals(weight, pet.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, weight);
    }

    @Override
    public String toString() {
        return String.format("Картотека домашних животных содержит - объект типа=%s, " +
                "id = %d, nickname = '%s', weight = %d, owner = %s", getClass().getSimpleName(), id, nickname, weight, owner);
    }
    @Override
    public int compareTo(Pet comparePets) {
        int result = this.nickname.compareTo(comparePets.nickname);
        if (result == 0) {
            result = this.weight.compareTo(comparePets.weight);
        }
        return result;
    }

}
