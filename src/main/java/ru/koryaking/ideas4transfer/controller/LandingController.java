package ru.koryaking.ideas4transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.koryaking.ideas4transfer.domain.Delivery;
import ru.koryaking.ideas4transfer.service.delivery_service.DeliveryService;


@Controller
@RequestMapping("/landings")
public class LandingController {

    private final DeliveryService deliveryService;

    public LandingController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("landings", deliveryService.getAll());
        return "landing-list";
    }

    @GetMapping("/new")
    public String getNewLandingForm(Model model) {
        model.addAttribute("landing", new Delivery());
        return "new-delivery";
    }

    @PostMapping("/new-landing")
    public String addNewLanding(Delivery delivery) {
        deliveryService.save(delivery);
        return "redirect:/landings";
    }
}
