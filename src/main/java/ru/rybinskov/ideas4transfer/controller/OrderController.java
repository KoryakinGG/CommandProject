package ru.rybinskov.ideas4transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;
import ru.rybinskov.ideas4transfer.dto.OrderViewDto;
import ru.rybinskov.ideas4transfer.service.order_service.OrderService;
import ru.rybinskov.ideas4transfer.service.user_service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    private final UserService userService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model, Principal principal) {
        model.addAttribute("simpleOrders", orderService.getAllSimplifiedViewOrdersByUser(principal.getName()));
        return "orders";
    }

    @GetMapping("/new-transfer-order")
    public String getFormatNewTransferOrder(Model model) {
        model.addAttribute("order", new OrderViewDto());
        return "new-transfer-order";
    }

    @PostMapping("/new-transfer-order")
    public String addOrder(@ModelAttribute OrderViewDto orderViewDto, Principal principal) {
        orderViewDto.setSender(principal.getName());
        orderViewDto.setUserId(userService.findByName(principal.getName()).getId());
        orderService.save(orderViewDto);
        return "redirect:/orders";
    }

    @PostMapping("/send-info")
    public String confirmOrder(@ModelAttribute OrderViewDto orderViewDto) {
        orderViewDto.setOrderStatus(OrderStatus.APPROVED_BY_MANAGER);
        orderService.update(orderViewDto);
        return "redirect:/orders";
    }

    @GetMapping("/confirm/{id}")
    public String confirmShipment(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order-confirm";
    }

    @PostMapping("/send-confirm")
    public String confirmShipment(@ModelAttribute OrderViewDto orderViewDto) {
        orderViewDto.setOrderStatus(OrderStatus.APPROVED_BY_TC);
        orderService.update(orderViewDto);
        return "redirect:/orders";
    }

    @GetMapping("/info/{id}")
    public String getOrder(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order-info";
    }

//    @GetMapping("/note/{id}")
//    public String getTransferNote(@PathVariable(name = "id") Long id, Model model) {
//        model.addAttribute("noteInfo", proxyOrderServiceImpl.getTransferNote(orderService.getOrderById(id)));
//        return "noteInfo";
//    }
//
//    @GetMapping("/change-status/{id}")
//    public String getTransferOrderStatus(@PathVariable(name = "id") Long id, Model model) {
//        // model.addAttribute("order", orderService.getOrderById(id));
//        // orderService.getOrderById(id).changeStatus(OrderStatus.APPROVED_BY_MANAGER);
//        return "redirect:/orders";
//    }
}
