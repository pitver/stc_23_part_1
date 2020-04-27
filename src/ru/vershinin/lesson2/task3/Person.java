package ru.vershinin.lesson2.task3;

import java.util.Objects;

/**
 * Person
 *
 * @author Вершинин Пётр
 */
public class Person  {
    private int age;
    private String name;
    Gender gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 100) {
            this.age = age;
        }else{
            System.out.println("возраст должен быть в диапазоне от 0 до 100, лет установлен по умолчанию 25");
            this.age = 25;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person() {
    }

    public Person(int age, String name, Gender gender) {
        if (age >= 0 && age <= 100) {
            this.age = age;
        }else{
            System.out.println("Для "+name+"возраст должен быть в диапазоне от 0 до 100 лет, установлен по умолчанию 25");
            this.age = 25;
        }
        this.name = name;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + gender +
                '}';
    }


    public int compareTo(Person p,boolean typeSort) {
        int result;
        if(typeSort) {
            result = this.name.compareTo(p.name);
        }else{
            result = this.gender.compareTo(p.gender);
        }
        return result;
    }

}
