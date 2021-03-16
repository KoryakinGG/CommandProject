package ru.koryaking.ideas4transfer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.koryaking.ideas4transfer.domain.Delivery;
import ru.koryaking.ideas4transfer.service.delivery_service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/landing")
public class RestLandingController {

    private DeliveryService deliveryService;

    @Autowired
    public RestLandingController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<Delivery> getAllLandings() {
        return deliveryService.getAll();
    }

    @GetMapping("/{id}")
    public Delivery getOneLandings(@PathVariable Long id) {
        return deliveryService.getLandingById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOneLandings(@PathVariable Long id) {
       deliveryService.deleteById(id);
        return "Ок";
    }

    @GetMapping("/new-landing")
    public String addLanding() throws JsonProcessingException {
        String json =   "[\n" +
                "  {\n" +
                "    \"0\": \"01.03.2021\",\n" +
                "    \"1\": \"в течение дня\",\n" +
                "    \"2\": \"внутренняя поставка\",\n" +
                "    \"3\": \"Фиат Дукато Р619ХА77\",\n" +
                "    \"4\": \"Павленко Андрей Евгеньевич  89257834209\",\n" +
                "    \"5\": \"GDH\",\n" +
                "    \"6\": \"GDH01000011-2021\",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"Goods House РИО Ленинский\",\n" +
                "    \"9\": \"Goods House Авеню\",\n" +
                "    \"10\": \"1\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"0\": \" \",\n" +
                "    \"1\": \"undefined\",\n" +
                "    \"2\": \"undefined\",\n" +
                "    \"3\": \"undefined\",\n" +
                "    \"4\": \"undefined\",\n" +
                "    \"5\": \"undefined\",\n" +
                "    \"6\": \"GDH01000013-2021\",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"undefined\",\n" +
                "    \"9\": \"HML 2 Uni Mall\",\n" +
                "    \"10\": \"9\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"0\": \" \",\n" +
                "    \"1\": \"undefined\",\n" +
                "    \"2\": \"undefined\",\n" +
                "    \"3\": \"undefined\",\n" +
                "    \"4\": \"undefined\",\n" +
                "    \"5\": \"undefined\",\n" +
                "    \"6\": \"КМР00044079-2021 \",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"undefined\",\n" +
                "    \"9\": \"Возврат поставщику Италтекс\",\n" +
                "    \"10\": \"6\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"0\": \" \",\n" +
                "    \"1\": \"undefined\",\n" +
                "    \"2\": \"undefined\",\n" +
                "    \"3\": \"undefined\",\n" +
                "    \"4\": \"undefined\",\n" +
                "    \"5\": \"CKH\",\n" +
                "    \"6\": \"СКН20000315-2021 CKH20000319-2021\",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"CKH Leninsky Tashir\",\n" +
                "    \"9\": \"Гудсхаус Авеню\",\n" +
                "    \"10\": \"2\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"0\": \" \",\n" +
                "    \"1\": \"undefined\",\n" +
                "    \"2\": \"undefined\",\n" +
                "    \"3\": \"undefined\",\n" +
                "    \"4\": \"undefined\",\n" +
                "    \"5\": \"undefined\",\n" +
                "    \"6\": \"CKH20000313-2021  СКН 20000320-2021\",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"undefined\",\n" +
                "    \"9\": \"Гудсхаус Академическая\",\n" +
                "    \"10\": \"2\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"0\": \" \",\n" +
                "    \"1\": \"undefined\",\n" +
                "    \"2\": \"undefined\",\n" +
                "    \"3\": \"undefined\",\n" +
                "    \"4\": \"undefined\",\n" +
                "    \"5\": \"undefined\",\n" +
                "    \"6\": \"CKH20000314-2021\",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"undefined\",\n" +
                "    \"9\": \"Гудсхаус Коломна\",\n" +
                "    \"10\": \"1\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"0\": \"01.03.2021\",\n" +
                "    \"1\": \"в течение дня\",\n" +
                "    \"2\": \"внутренняя поставка\",\n" +
                "    \"3\": \"undefined\",\n" +
                "    \"4\": \"undefined\",\n" +
                "    \"5\": \"GDH\",\n" +
                "    \"6\": \"GDH02000016-2021\",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"GDH Avenue\",\n" +
                "    \"9\": \"Перемещение на склад HML 2 Winny Rublevka\",\n" +
                "    \"10\": \"14\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"0\": \" \",\n" +
                "    \"1\": \"undefined\",\n" +
                "    \"2\": \"undefined\",\n" +
                "    \"3\": \"undefined\",\n" +
                "    \"4\": \"undefined\",\n" +
                "    \"5\": \"undefined\",\n" +
                "    \"6\": \"GDH02000015-2021\",\n" +
                "    \"7\": \"кросс-докинг\",\n" +
                "    \"8\": \"undefined\",\n" +
                "    \"9\": \"для Коломны\",\n" +
                "    \"10\": \"1\",\n" +
                "    \"11\": \"нет данных\",\n" +
                "    \"12\": \"нет данных\\n\"\n" +
                "  }\n"+
                "]";
        ObjectMapper objectMapper = new ObjectMapper();
//        Landing landing = objectMapper.readValue(json, Landing.class);
//        landingService.save((Landing) landingList);
        List<Delivery> deliveryList = objectMapper.readValue(json, new TypeReference<List<Delivery>>() {});
        deliveryService.saveList(deliveryList);
        return "Congratulation";
    }


}
