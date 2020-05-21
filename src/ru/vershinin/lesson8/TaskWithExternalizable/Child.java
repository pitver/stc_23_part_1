package ru.vershinin.lesson8.TaskWithExternalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Child
 *
 * @author Вершинин Пётр
 */
public class Child extends Person implements Externalizable {

    private static final long serialVersionUID = -8548526745599278939L;
    protected String profession;
    protected int salary;

    public Child() {

    }

    public Child(String profession, int salary, String name, int age) {
        super(name, age);
        this.profession = profession;
        this.salary = salary;
        System.out.println("Child:Constructor");
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(name);
        out.writeInt(age);

        out.writeObject(profession);
        out.writeInt(salary);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
      name=(String)in.readObject();
      age=in.readInt();

      profession=(String)in.readObject();
      salary=in.readInt();
    }
}
