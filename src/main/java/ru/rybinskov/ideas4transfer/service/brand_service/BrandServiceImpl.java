package ru.rybinskov.ideas4transfer.service.brand_service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.domain.Delivery;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.BrandRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService{


   private final BrandRepository brandRepository;


    @Override
    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream().map(BrandDto::new).collect(Collectors.toList());
    }

    @Override
    public BrandDto findById(Long id) throws ResourceNotFoundException {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Поставка по указанному id не найдена:  id = " + id));
        return new BrandDto(brand);
    }

    @Override
    public BrandDto save(BrandDto brandDto) throws ResourceNotFoundException, WarehouseException {
        if (brandDto.getId() == null) {
            return new BrandDto(brandRepository.save(new Brand(brandDto)));
        }
        Brand brand = brandRepository.findById(brandDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Бренд с id = " + brandDto.getId() + " не найден"));
        brand.updateFields(brandDto);
        return new BrandDto(brandRepository.save(brand));
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<BrandDto> brandDtos) {
        List<Brand> brands = brandDtos.stream().map(Brand::new).collect(Collectors.toList());
        brandRepository.saveAll(brands);
    }
}
