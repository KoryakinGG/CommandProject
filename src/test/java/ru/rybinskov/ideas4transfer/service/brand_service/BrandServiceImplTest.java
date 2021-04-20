package ru.rybinskov.ideas4transfer.service.brand_service;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrandServiceImplTest {

    List<Brand> brandList;
    List<BrandDto> brandDtoList;
    Brand brand;
    BrandDto brandDto;

    @Autowired
    BrandServiceImpl brandServiceImpl;

    @MockBean
    BrandRepository brandRepository;

    @MockBean
    List<Shop> shops;

    @BeforeEach
    void init() {
        brandList = new ArrayList<>();
        brandList.add(new Brand(1L,"GoodBeer", "GB", shops));
        brandList.add(new Brand(2L,"BadBeer", "BB", shops));
        brandList.add(new Brand(3L,"GreatBeer", "GRB", shops));

        brandDtoList = new ArrayList<>();
        brandDtoList.add(new BrandDto(1L,"GoodBeer", "GB"));
        brandDtoList.add(new BrandDto(2L,"BadBeer", "BB"));
        brandDtoList.add(new BrandDto(3L,"GreatBeer", "GRB"));

        brand = Brand.builder()
                .id(1L)
                .name("GoodBeer")
                .abbr("GB")
                .build();

        brandDto = BrandDto.builder()
                .name("BadBeer")
                .abbr("BB")
                .build();
    }

    @AfterEach
    void close() {
        brandList.clear();
        brandDtoList.clear();
    }
    //TestMethod_Condition_ExpectedResult
    @Test
    void givenAllBrandDto_whenBrandServiceFindAll_thenOk() {
        Mockito.doReturn(brandList)
                .when(brandRepository)
                .findAll();

        List<BrandDto> brandDto = brandServiceImpl.findAll();
        assertEquals(brandList.size(),brandDto.size());
        Mockito.verify(brandRepository, Mockito.times(1)).findAll();
    }

    @Test
    void givenBrandDto_whenBrandServiceFindById_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(1L);

        BrandDto brandDto = brandServiceImpl.findById(1L);
        assertNotNull(brandDto);
        Mockito.verify(brandRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }

    @Test
    void givenResourceNotFoundException_whenBrandIdIsNotFound() {
        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(1L);
        brandDto.setId(3L);
        assertThrows(ResourceNotFoundException.class, () -> {
            brandServiceImpl.save(brandDto);
        });
    }

    @Test
    void givenDeleteBrandById_whenBrandServiceDeleteById_thenOk() {
        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(1L);

        brandServiceImpl.delete(1L);
        assertEquals(0, brandRepository.findAll().size());
        Mockito.verify(brandRepository, Mockito.times(1)).deleteById(brand.getId());
    }

    @Test
    void givenSaveAllBrands_whenBrandServiceSaveAll_thenOk() {
        List<Brand> brands = brandDtoList.stream().map(Brand::new).collect(Collectors.toList());

        Mockito.doReturn(brands)
                .when(brandRepository)
                .findAll();

        brandServiceImpl.saveAll(brandDtoList);
        assertEquals(brands.size(), brandRepository.findAll().size());
        Mockito.verify(brandRepository, Mockito.times(1)).saveAll(ArgumentMatchers.eq(brands));
    }

    @Test
    void save() throws ResourceNotFoundException, WarehouseException {

        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(Mockito.any());

        Mockito.doReturn(brand)
                .when(brandRepository)
                .save(Mockito.any());

        brandServiceImpl.save(brandDto);
        assertNotNull(brandRepository.findAll());
    }
}