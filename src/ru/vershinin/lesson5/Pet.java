package ru.vershinin.lesson5;

import java.util.*;

/**
 * картотека домашних животных
 * Animal
 *
 * @author Вершинин Пётр
 */
public class Pet implements Comparable<Pet> {

    /**
     * счетчик, применяется для создания уникального  идентификационного номера
     */
    private static int count = 1;

    /**
     * общий список домашних животных
     */
    List<Pet> listPets = new ArrayList<Pet>();

    /**
     * уникальный идентификационный номер, int
     */
    private int id;
    /**
     * кличка животного, String
     */
    private String nickname;
    /**
     * хозяин животного
     * @see Person
     */
    private Person owner;
    /**
     * вес животного, Integer
     */
    private Integer weight;

    public Pet() {
    }

    /**
     * Конструтор объекта Pet
     * @param nickname
     * @param owner
     * @param weight
     */
    public Pet(String nickname, Person owner, int weight) {
        id = count++;
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }


    /**
     * добавления животного в общий список
     * добавление дубликатов приводит к исключительной ситуации СoincidenceException
     * @param pets -добавленное животное
     */
    public void addAnimals(Pet pets) {

        try {
            if (listPets.isEmpty()) {
                listPets.add(pets);
            } else {
                if (listPets.contains(pets)) {
                    throw new СoincidenceException("выявлено совпадние -" + pets + "-  уже существует");
                } else {
                    listPets.add(pets);
                }
            }
        } catch (СoincidenceException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * поиск животного по его кличке
     * boolean check-флаг указывающий на совпадение по кличке
     * @param nickName - кличка животного, которое необходимо найти
     */
    public void searchByNickname(String nickName) {
        boolean check = false;
        for (Pet s : listPets) {
            if (s.getNickname().equals(nickName)) {
                System.out.println("найден объект с именем -" + nickName + " в " + s);
                System.out.println("-------------------------------------------------------------");
                check = true;
            }
        }
        if (!check) {
            System.out.println("совпадений по кличке -" + nickName + " результатов не дал");
            System.out.println("-------------------------------------------------------------");
        }
    }

    /**
     * изменение данных животного по его идентификатору
     *
     * @param id-идентификатор по которому идет идет поиск объекта которое нужно изменить
     * @param nik - новое значение клички животного
     * @param wt - новое значение веса животного
     */
    public void updatePet(int id, String nik, int wt) {
        for (Pet s : listPets) {
            if (s.getId() == id) {
                s.setNickname(nik);
                s.setWeight(wt);
            }
        }
    }


    /**
     * вывод на экран списка животных в отсортированном порядке.
     * Поля для сортировки –  хозяин, кличка животного, вес.
     */
    public void print() {
        Comparator<Pet> pc = new CompNikeName().thenComparing(new CompWeight());
        TreeSet<Pet> pet = new TreeSet(pc);
        pet.addAll(listPets);
        for (Pet listAnimal : pet) {
            System.out.println(listAnimal);
        }
        System.out.println("-------------------------------------------------------------");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
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
    public int compareTo(Pet o) {
        int result = this.nickname.compareTo(o.nickname);
        if (result == 0) {
            result = this.weight.compareTo(o.weight);
        }
        return result;
    }

    /**
     * компоратор по весу животного
     */
    public static class CompWeight implements Comparator<Pet> {
        @Override
        public int compare(Pet o1, Pet o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }

    /**
     * компоратор по кличке животного
     */
    public static class CompNikeName implements Comparator<Pet> {
        @Override
        public int compare(Pet o1, Pet o2) {
            return o1.getNickname().compareTo(o2.getNickname());
        }
    }

    /**
     * компоратор по хозяину
     */
    public static class CompOwner implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }


}
