package ru.vershinin.lesson11;

/**
 * ActionsWithPets
 *
 * @author Вершинин Пётр
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

/**
 * ActionsWithPets
 *
 * @author Вершинин Пётр
 */
public class ActionsWithPets {
    /**
     * общий список домашних животных
     */
    Map<String, Pet> listPets = new HashMap<>();

    /**
     * добавления животного в общий список
     * добавление дубликатов приводит к исключительной ситуации СoincidenceException
     *
     * @param pets -добавленное животное
     */
    public void addAnimals(Pet pets) {

        try {
            if (listPets.isEmpty()) {
                listPets.put(pets.nickname, pets);
            } else {
                if (listPets.containsValue(pets)) {
                    throw new СoincidenceException("выявлено совпадние -" + pets + "-  уже существует");
                } else {
                    listPets.put(pets.nickname, pets);
                }
            }
        } catch (СoincidenceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * поиск животного по его кличке
     *
     * @param nickName - кличка животного, которое необходимо найти
     */
    public void searchByNickname(String nickName) {// Hash

        Optional<Map.Entry<String, Pet>> optionalS=listPets.entrySet().stream()
                .filter(s -> s.getKey().contains(nickName))
                .findAny();
        if(optionalS.isPresent()) {
            System.out.println("найден объект с именем -" + nickName + " в " + optionalS.get());
        }else {
            System.out.println("поиск совпадений по кличке -" + nickName + " результатов не дал");
        }
        System.out.println("-------------------------------------------------------------");

    }

    /**
     * изменение данных животного по его идентификатору
     *
     * @param id-идентификатор по которому идет идет поиск объекта которое нужно изменить
     * @param nik              - новое значение клички животного
     * @param wt               - новое значение веса животного
     */
    public void updatePet(int id, String nik, int wt) {

        listPets.entrySet().stream()
                .filter(s -> s.getValue().getId() == id)
                .forEach(d -> {d.getValue().setNickname(nik);
                               d.getValue().setWeight(wt);});
    }

    /**
     * вывод на экран списка животных в отсортированном порядке.
     * Поля для сортировки –  хозяин, кличка животного, вес.
     */
    public void print() {
        TreeSet<Pet> pet = new TreeSet<>(listPets.values());

        pet.stream()
                .forEach(s-> System.out.println(s));
        System.out.println("-------------------------------------------------------------");
    }
}
