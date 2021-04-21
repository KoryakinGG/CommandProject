package ru.rybinskov.ideas4transfer.service.shop_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.domain.Shop;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopServiceImplTest {

    @Autowired
    ShopServiceImpl shopService;

    @MockBean
    ShopRepository shopRepository;

    @MockBean
    Brand brand;

    @MockBean
    BrandDto brandDto;

    List<Shop> shopList;
    List<ShopDto> shopDtoList;
    Shop shop;
    ShopDto shopDto;

    @BeforeEach
    void setUp() {
        shopList = new ArrayList<>();
        shopList.add(new Shop(1L, "Government Department Store", "GDS", brand));
        shopList.add(new Shop(2L, "Central Department Store", "CDS", brand));
        shopList.add(new Shop(3L, "My Private Shop", "MPS", brand));

        shopDtoList = new ArrayList<>();
        shopDtoList.add(new ShopDto(1L,"Green City", "GS", brandDto));
        shopDtoList.add(new ShopDto(2L,"Gloria Jeans", "GJ", brandDto));
        shopDtoList.add(new ShopDto(3L,"H&M", "HM", brandDto));

        shop = new Shop(1L, "Magnit", "MG", brand);
        shopDto = new ShopDto(1L, "Aushan", "AS", brandDto);
    }

    @AfterEach
    void tearDown() {
        shopList.clear();
        shopDtoList.clear();
    }

    @Test
    void givenShopDto_whenShopServiceFindById_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(shop))
                .when(shopRepository)
                .findById(Mockito.any());

        ShopDto shopDto = shopService.findById(1L);
        assertNotNull(shopDto);
        Mockito.verify(shopRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void givenAllShopDto_whenShopServiceFindAll_thenOk() {
        Mockito.doReturn(shopList)
                .when(shopRepository)
                .findAll();

        List<ShopDto> shopDto = shopService.findAll();
        assertEquals(shopList.size(),shopDto.size());
        Mockito.verify(shopRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenUpdateShops_whenShopServiceUpdate_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(shop))
                .when(shopRepository)
                .findById(Mockito.any());

        Mockito.doReturn(shop)
                .when(shopRepository)
                .save(Mockito.any());

        shopService.updateShop(shopDto);
        Mockito.verify(shopRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void givenSaveShops_whenShopServiceSave_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(shop))
                .when(shopRepository)
                .findById(Mockito.any());

        Mockito.doReturn(shop)
                .when(shopRepository)
                .save(Mockito.any());

        ShopDto report = shopService.save(shopDto);
        assertNotNull(report);
    }

    @Test
    void givenDeleteShopById_whenShopServiceDeleteById_thenOk() {
        Mockito.doNothing().when(shopRepository).deleteById(1L);
        shopService.delete(1L);
        Mockito.verify(shopRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void givenSaveAllShops_whenShopServiceSaveAll_thenOk() {
        List<Shop> shops = shopDtoList.stream().map(Shop::new).collect(Collectors.toList());

        Mockito.doReturn(shops)
                .when(shopRepository)
                .findAll();

        shopService.saveAll(shopDtoList);
        assertEquals(shops.size(), shopRepository.findAll().size());
        Mockito.verify(shopRepository, Mockito.times(1)).saveAll(shops);
    }

    @Test
    void givenResourceNotFoundException_whenShopIdIsNotFound() {
        Mockito.doReturn(Optional.of(shop))
                .when(shopRepository)
                .findById(1L);
        shopDto.setId(3L);
        assertThrows(ResourceNotFoundException.class, () -> {
            shopService.save(shopDto);
        });
    }
}