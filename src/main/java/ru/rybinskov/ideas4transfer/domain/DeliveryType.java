package ru.rybinskov.ideas4transfer.domain;

public enum DeliveryType {
    //при входе будем делать toLowerCase
    CROSS_DOCKING("кросс-докинг"), TO_WAREHOUSE("на склад");

    private final String name;

    DeliveryType(String name) {
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

    public static DeliveryType valueOfOrDefault(String myValue) {
        for (DeliveryType type : DeliveryType.class.getEnumConstants()) {
            if (type.toString().equals(myValue.toLowerCase())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Введено запрещенное значение");
    }
}
