package ru.koryaking.ideas4transfer.service.delivery_service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryaking.ideas4transfer.domain.*;
import ru.koryaking.ideas4transfer.repository.BrandRepository;
import ru.koryaking.ideas4transfer.repository.DeliveryRepository;

import org.json.simple.parser.JSONParser;
import ru.koryaking.ideas4transfer.repository.ShopRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;
    private BrandRepository brandRepository;
    private ShopRepository shopRepository;

    @Autowired
    public void setLandingRepository(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery getLandingById(Long id) {
        return deliveryRepository.findLandingById(id);
    }

    @Override
    public Delivery save(Delivery delivery) {return deliveryRepository.save(delivery);}

    @Override
    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public List<Delivery> getAllSimplifiedLandingByUser(String username) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public void saveList(List<Delivery> list) {
        deliveryRepository.saveAll(list);
    }

    @Override
    public String saveJson(Delivery delivery) throws ParseException {
//            JSONParser parser = new JSONParser();
//            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
//            JSONObject deliveryJsonObject = (JSONObject) parser.parse(resultJson);
//            //фигарим переменные по полям
//            LocalDate localDate = (LocalDate) deliveryJsonObject.get("Дата прибытия на склад");
//            DeliveryTime deliveryTime = (DeliveryTime) deliveryJsonObject.get("Плановое время прибытия на склад");
//            String carInfo = (String) deliveryJsonObject.get("Марка и номер ТС");
//            String driverInfo = (String) deliveryJsonObject.get("ФИО водителя и телефон");
//            Brand brand = (Brand) deliveryJsonObject.get("Бренд");
//            String orderNumber = (String) deliveryJsonObject.get("ВЗ");
//            DeliveryType deliveryType = (DeliveryType) deliveryJsonObject.get("Тип поставки");
//            String sendor = (String) deliveryJsonObject.get("Поставщик");
//            String comment = (String) deliveryJsonObject.get("Комментарий");
//            Shop shop = (Shop) deliveryJsonObject.get("Магазин");
//            String numberOfPlaces = (String) deliveryJsonObject.get("Кол-во мест");
//            String torgNumber = (String) deliveryJsonObject.get("№ Торг");
//            String invoice = (String) deliveryJsonObject.get("№ счет-фактуры");
            // пихаем все это в delivery
//             delivery = Delivery.builder()
//                             .deliveryDate(localDate)
//                             .deliveryTime(deliveryTime)
//                             .carInfo(carInfo)
//                             .driverInfo(driverInfo)
//                             .brand(brand)
//                             .orderNumber(orderNumber)
//                             .deliveryType(deliveryType)
//                             .sendor(sendor)
//                             .comment(comment)
//                             .shop(shop)
//                             .numberOfPlaces(numberOfPlaces)
//                             .torgNumber(torgNumber)
//                             .invoice(invoice).build();
        deliveryRepository.save(delivery);
        return "Well Done";
    }
}
