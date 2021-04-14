package ru.rybinskov.ideas4transfer.service.brand_service;

import org.aspectj.weaver.patterns.IVerificationRequired;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.domain.Shop;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.BrandRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrandServiceImplTest {

    private static List<Brand> brandList;
    private static List<BrandDto> brandDtoList;
    private static Brand brandMock;

    @Autowired
    private BrandServiceImpl brandServiceImpl;

    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    private static List<Shop> shops;

    @BeforeAll
    static void initAll() {
        brandList = new ArrayList<>();
        brandList.add(new Brand(1L,"GoodBeer", "GB", shops));
        brandList.add(new Brand(2L,"BadBeer", "BB", shops));
        brandList.add(new Brand(3L,"GreatBeer", "GRB", shops));

        brandDtoList = new ArrayList<>();
        brandDtoList.add(new BrandDto(1L,"GoodBeer", "GB"));
        brandDtoList.add(new BrandDto(2L,"BadBeer", "BB"));
        brandDtoList.add(new BrandDto(3L,"GreatBeer", "GRB"));

        brandMock = new Brand();
        brandMock.setId(1L);
        brandMock.setName("GoodBeer");
        brandMock.setAbbr("GB");
        brandMock.setShops(shops);
    }

    @Test
    void findAll() {
        Mockito.doReturn(brandList)
                .when(brandRepository)
                .findAll();

        List<BrandDto> brandDto = brandServiceImpl.findAll();
        Assertions.assertEquals(brandList.size(),brandDto.size());
        Mockito.verify(brandRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findById() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(brandMock))
                .when(brandRepository)
                .findById(1L);

        BrandDto brandDto = brandServiceImpl.findById(1L);
        Assertions.assertNotNull(brandDto);
        Mockito.verify(brandRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }

    @Test
    void findByIdError() throws ResourceNotFoundException {
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
        });
    }

    @Test
    void delete() {
        Mockito.doReturn(Optional.of(brandMock))
                .when(brandRepository)
                .findById(1L);

        brandServiceImpl.delete(1L);
        Assertions.assertEquals(0, brandRepository.findAll().size());
        Mockito.verify(brandRepository, Mockito.times(1)).deleteById(brandMock.getId());
    }

    @Test
    void saveAll() {
        List<Brand> brands = brandDtoList.stream().map(Brand::new).collect(Collectors.toList());

        Mockito.doReturn(brands)
                .when(brandRepository)
                .findAll();

        brandServiceImpl.saveAll(brandDtoList);
        Assertions.assertEquals(brands.size(), brandRepository.findAll().size());
        Mockito.verify(brandRepository, Mockito.times(1)).saveAll(ArgumentMatchers.eq(brands));
    }

    @Test
    void save() throws ResourceNotFoundException, WarehouseException {

        Brand brand = Brand.builder()
                .id(1L)
                .name("GoodBeer")
                .abbr("GB")
                .build();

        BrandDto brandDto = BrandDto.builder()
                .name("BadBeer")
                .abbr("BB")
                .build();

        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(1L);

        BrandDto brandDto1 = new BrandDto(1L,"BadBeer", " BB");
        brandServiceImpl.save(brandDto);
        Assertions.assertNotNull(brandRepository.findAll());
    }
}