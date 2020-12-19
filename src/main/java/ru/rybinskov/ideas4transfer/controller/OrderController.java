package ru.rybinskov.ideas4transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.dao.MockBD;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrderDetails;
import ru.rybinskov.ideas4transfer.service.order_service.OrderNodeService;
import ru.rybinskov.ideas4transfer.service.order_service.OrderService;
import ru.rybinskov.ideas4transfer.service.order_service.ProxyOrderServiceImpl;


@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderNodeService orderNodeService;
    private final ProxyOrderServiceImpl proxyOrderServiceImpl;

    public OrderController(OrderService orderService, OrderNodeService orderNodeService, ProxyOrderServiceImpl proxyOrderServiceImpl) {
        this.orderService = orderService;
        this.orderNodeService = orderNodeService;
        this.proxyOrderServiceImpl = proxyOrderServiceImpl;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "orders";
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

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("orderInfo", orderService.getOrderById(id).toString());
        return "orderInfo";
    }

    @GetMapping("/note/{id}")
    public String getTransferNote(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("noteInfo", proxyOrderServiceImpl.getTransferNote(orderService.getOrderById(id)));
        return "noteInfo";
    }

    @GetMapping("/change-status/{id}")
    public String getTransferOrderStatus(@PathVariable(name = "id") Long id, Model model) {
       // model.addAttribute("order", orderService.getOrderById(id));
        orderService.getOrderById(id).changeStatus(OrderStatus.APPROVED_BY_MANAGER);
        return "redirect:/orders";
    }
}
