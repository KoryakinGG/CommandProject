package ru.rybinskov.ideas4transfer.service.shop_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.Shop;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.ShopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;


    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public ShopDto findById(Long id) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Магазин по указанному id не найден: id = " + id));
        return new ShopDto(shop);
    }

    @Override
    public List<ShopDto> findAll() {
        return shopRepository.findAll().stream().map(ShopDto::new).collect(Collectors.toList());
    }

    @Override
    public void updateShop(ShopDto shopDto) throws ResourceNotFoundException {
        ShopDto shop = findById(shopDto.getId());
        shop.updateAllFieldsWithoutId(shopDto);
        shopRepository.save(new Shop(shop));
    }

    @Override
    public void save(ShopDto shopDto) {
        shopRepository.save(new Shop(shopDto));
    }

    @Override
    public void delete(ShopDto shopDto) {
        shopRepository.delete(new Shop(shopDto));
    }

    @Override
    public void saveAll(List<ShopDto> shopDtos) {
        List<Shop> shops = shopDtos.stream().map(Shop::new).collect(Collectors.toList());
        shopRepository.saveAll(shops);
    }
}
