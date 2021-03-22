package ru.rybinskov.ideas4transfer.domain.example;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    //Всё, что ниже важно, т.к. enum не поддерживает пробелы =(
    @Override
    public String toString() {
        return gender;
    }

}
