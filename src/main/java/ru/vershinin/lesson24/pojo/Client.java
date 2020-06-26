package ru.vershinin.lesson24.pojo;


/**
 * Client
 *
 * @author Вершинин Пётр
 */

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

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
    public static class Builder {

        private final Client newclient;

        public Builder() {
            newclient = new Client();
        }




        public Builder withId(Integer id){
            newclient.id=id;
            return this;
        }

        public Builder withFirstName(String firstName){
            newclient.firstName=firstName;
            return this;
        }
        public Builder withLastName(String lastName){
            newclient.lastName=lastName;
            return this;
        }
        public Builder withUsername(String username){
            newclient.username=username;
            return this;
        }
        public Builder withPassword(String password){
            newclient.password=password;
            return this;
        }
        public Client build(){
            return newclient;
        }

    }
}

