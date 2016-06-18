package com.myfirstprogramm.sokol.myapplication;
public class User {

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    private String Name;
    private String LastName;
    private int Age;

    public User(String Name,String LastName, int Age){
        this.Name = Name;
        this.LastName = LastName;
        this.Age = Age;



    }
}
