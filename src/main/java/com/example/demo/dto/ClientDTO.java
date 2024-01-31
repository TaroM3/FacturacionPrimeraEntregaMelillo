package com.example.demo.dto;

import jakarta.persistence.Entity;

import java.util.Date;


public class ClientDTO {

    private String name;
    private String surname;
    private long age;
    public ClientDTO(String name, String surname, Date birthday){
        this.name = name;
        this.surname = surname;

        this.age = AgeCalculator(birthday);
    }

    private long AgeCalculator(Date birthday){
        Date now = new Date();
        long difference = now.getTime() - birthday.getTime();
        long age = difference / (1000L * 60 * 60 * 24 * 365);
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public long getAge() {
        return age;
    }
}
