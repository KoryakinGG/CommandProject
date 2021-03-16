package ru.koryaking.ideas4transfer.service.brand_service;

import ru.koryaking.ideas4transfer.domain.Brand;

import java.util.List;

public interface BrandService {
    Brand getBrandById(Long id);
    void save(Brand brand);
    List<Brand> getAll();
    void deleteById (Long id);
    void saveList(List<Brand> list);
}
