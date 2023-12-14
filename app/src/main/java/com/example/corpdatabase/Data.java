package com.example.corpdatabase;

//класс для передачи данных в sql-запрос
public class Data {

    String name;
    String surname;
    int year;

    public Data(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

}
