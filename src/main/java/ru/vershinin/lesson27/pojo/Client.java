package ru.vershinin.lesson27.pojo;


/**
 * client с реализацией паттерна Строитель
 *
 * @author Вершинин Пётр
 */

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String roles;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    private Client() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public static class Builder {

        private final Client newClient;

        public Builder() {
            newClient = new Client();
        }

        public Builder withId(Integer id) {
            newClient.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            newClient.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            newClient.lastName = lastName;
            return this;
        }

        public Builder withUsername(String username) {
            newClient.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            newClient.password = password;
            return this;
        }
        public Builder withRole(String roles) {
            newClient.roles=roles;
            return this;
        }

        public Client build() {
            return newClient;
        }

    }
}


