package ru.rybinskov.ideas4transfer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class Slf4jController {

    @GetMapping("/logger")
    public void showLoggers() {
        log.info("Привет это логгер - INFO");
        log.warn("Привет это логгер - WARNING");
        log.error("Привет это логгер - ERROR");
    }
}