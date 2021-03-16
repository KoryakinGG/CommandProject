package ru.koryaking.ideas4transfer.service.brand_service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.koryaking.ideas4transfer.domain.Brand;
import ru.koryaking.ideas4transfer.repository.BrandRepository;

import java.util.List;

public class BrandServiceImpl implements BrandService{

    BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findBrandById(id);
    }

    @Override
    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void saveList(List<Brand> list) {
        brandRepository.saveAll(list);
    }
}
