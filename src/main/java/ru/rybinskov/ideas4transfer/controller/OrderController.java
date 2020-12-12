package ru.rybinskov.ideas4transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dao.OrderDao;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrderDetails;
import ru.rybinskov.ideas4transfer.service.order_service.OrderService;


@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderDao orderDao;
    private final OrderService orderService;

    public OrderController(OrderDao orderDao, OrderService orderService) {
        this.orderDao = orderDao;
        this.orderService = orderService;
    }

    @GetMapping("/new-transfer-order")
    public String getFormatNewTransferOrder(Model model) {
        model.addAttribute("detail", new TransferOrderDetails());
        return "new-transfer-order";
    }

    @PostMapping("/new-transfer-order")
    public String addTransferOrder(TransferOrderDetails detail) {
        System.out.println("Отработал пост запрос, кол-во коробов: " + detail.getNumberOfBoxes());
        //создадим заказ, добавим в него TransferOrderDetails и сохраним
        return "redirect:/orders";
    }
}
