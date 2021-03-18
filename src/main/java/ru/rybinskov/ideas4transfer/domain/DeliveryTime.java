package ru.rybinskov.ideas4transfer.domain;

public enum DeliveryTime {

    DURING_A_DAY("в течение дня"), MORNING("первая половина дня"), AFTERNOON("вторая половина дня");

    private final String name;

    DeliveryTime(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //Всё, что ниже важно, т.к. enum не поддерживает пробелы =(
    @Override
    public String toString() {
        return name;
    }

    public static DeliveryTime valueOfOrDefault(String myValue) {
        for(DeliveryTime time : DeliveryTime.class.getEnumConstants()) {
            if(time.toString().equals(myValue.toLowerCase())) {
                return time;
            }
        }
        throw new IllegalArgumentException("Введено запрещенное значение");
    }



}
