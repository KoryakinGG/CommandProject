package ru.koryaking.ideas4transfer.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import ru.koryaking.ideas4transfer.domain.*;
import ru.koryaking.ideas4transfer.service.brand_service.BrandService;
import ru.koryaking.ideas4transfer.service.delivery_service.DeliveryService;
import ru.koryaking.ideas4transfer.service.shop_service.ShopService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v2/delivery")
public class SimpleJSONController {

    DeliveryService deliveryService;
    BrandService brandService;
    ShopService shopService;

    @GetMapping
    public List<Delivery> getAllLandings() {
        return deliveryService.getAll();
    }

    @GetMapping("/incoming-json")
    public String saveNewDeleviry () throws ParseException, java.text.ParseException {
        String sJson = "{\n" +
                "    \"Дата прибытия на склад\": \"01.03.2021\",\n" +
                "    \"Плановое время прибытия на склад\": \"в течение дня\",\n" +
                "    \"Марка и номер ТС\": \"Фиат Дукато Р619ХА77\",\n" +
                "    \"ФИО водителя и телефон\": \"Павленко Андрей Евгеньевич  89257834209\",\n" +
                "    \"Бренд\": \"GDH\",\n" +
                "    \"ВЗ\": \"GDH01000011-2021\",\n" +
                "    \"Тип поставки\": \"кросс-докинг\",\n" +
                "    \"Поставщик\": \"Goods House РИО Ленинский\",\n" +
                "    \"Комментарий\": \"Goods House Авеню\",\n" +
                "    \"Магазин\": \"внутренняя поставка\",\n" +
                "    \"Кол-во мест\": \"1\",\n" +
                "    \"№ Торг\": \"нет данных\",\n" +
                "    \"№ счет-фактуры\": \"нет данных\\n\"\n" +
                "  }";
        JSONParser parser = new JSONParser();
        JSONObject deliveryJsonObject = (JSONObject) parser.parse(sJson);

        DateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date dutyDay = (java.sql.Date) simpleDateFormat.parse("Дата прибытия на склад");

        LocalDate localDate = (LocalDate) deliveryJsonObject.get(dutyDay);
        DeliveryTime deliveryTime = (DeliveryTime) deliveryJsonObject.get("Плановое время прибытия на склад");
        String carInfo = (String) deliveryJsonObject.get("Марка и номер ТС");
        String driverInfo = (String) deliveryJsonObject.get("ФИО водителя и телефон");
        Brand brand = (Brand) deliveryJsonObject.get("Бренд");
        String orderNumber = (String) deliveryJsonObject.get("ВЗ");
        DeliveryType deliveryType = (DeliveryType) deliveryJsonObject.get("Тип поставки");
        String sendor = (String) deliveryJsonObject.get("Поставщик");
        String comment = (String) deliveryJsonObject.get("Комментарий");
        Shop shop = (Shop) deliveryJsonObject.get("Магазин");
        String numberOfPlaces = (String) deliveryJsonObject.get("Кол-во мест");
        String torgNumber = (String) deliveryJsonObject.get("№ Торг");
        String invoice = (String) deliveryJsonObject.get("№ счет-фактуры");

        Delivery delivery = Delivery.builder()
                .deliveryDate(localDate)
                .deliveryTime(deliveryTime)
                .carInfo(carInfo)
                .driverInfo(driverInfo)
                .brand(brand)
                .orderNumber(orderNumber)
                .deliveryType(deliveryType)
                .sendor(sendor)
                .comment(comment)
                .shop(shop)
                .numberOfPlaces(numberOfPlaces)
                .torgNumber(torgNumber)
                .invoice(invoice).build();

        deliveryService.saveJson(delivery);

        return "Well Done";
    }
}
