package ru.rybinskov.ideas4transfer.service.shop_service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.domain.Shop;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.ShopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public ShopDto findById(Long id) throws ResourceNotFoundException {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Магазин по указанному id не найден: id = " + id));
        log.info("Working method ShopService findById {}", shop);
        return new ShopDto(shop);
    }

    @Override
    public List<ShopDto> findAll() {
        log.info("Working method ShopService findAll");
        return shopRepository.findAll().stream().map(ShopDto::new).collect(Collectors.toList());
    }

    @Override
    public void updateShop(ShopDto shopDto) throws ResourceNotFoundException {
        ShopDto shop = findById(shopDto.getId());
        shop.updateAllFieldsWithoutId(shopDto);
        shopRepository.save(new Shop(shop));
        log.info("Working method ShopService updateShop {}", shop);
    }

    @Override
    public ShopDto save(ShopDto shopDto) throws ResourceNotFoundException, WarehouseException {
        if (shopDto.getId() == null) {
            log.info("Working method ShopService save {} is null, create new", shopDto.getId());
            return new ShopDto(shopRepository.save(new Shop(shopDto)));
        }
        Shop shop = shopRepository.findById(shopDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Магазин с id = " + shopDto.getId() + " не найден"));
        shop.updateFields(shopDto);
        log.info("Working method ShopService save {}", shop);
        return new ShopDto(shopRepository.save(shop));
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
        log.info("Working method ShopService delete {}", id);
    }

    @Override
    public void saveAll(List<ShopDto> shopDtos) {
        List<Shop> shops = shopDtos.stream().map(Shop::new).collect(Collectors.toList());
        shopRepository.saveAll(shops);
        log.info("Working method ShopService saveAll {}", shops);
    }
}
