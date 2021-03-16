package ru.koryaking.ideas4transfer.service.shop_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryaking.ideas4transfer.domain.Shop;
import ru.koryaking.ideas4transfer.repository.ShopRepository;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }


    @Override
    public Shop getShopById(Long id) {
        return shopRepository.findShopById(id);
    }

    @Override
    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public void saveList(List<Shop> list) {
        shopRepository.saveAll(list);
    }
}
