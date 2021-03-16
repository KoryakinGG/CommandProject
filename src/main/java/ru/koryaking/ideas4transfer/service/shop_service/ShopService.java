package ru.koryaking.ideas4transfer.service.shop_service;

import ru.koryaking.ideas4transfer.domain.Shop;

import java.util.List;

public interface ShopService {

    Shop getShopById(Long id);
    void save(Shop shop);
    List<Shop> getAll();
    void deleteById (Long id);
    void saveList(List<Shop> list);
}
