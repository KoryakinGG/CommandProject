package ru.rybinskov.ideas4transfer.service.brand_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.domain.Delivery;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.BrandRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService{

   private BrandRepository brandRepository;

   @Autowired
   public void setBrandRepository(BrandRepository brandRepository) {
       this.brandRepository = brandRepository;
   }

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
    public void createBrand(BrandDto brandDto) {
        brandRepository.save(new Brand(brandDto));
    }

    @Override
    public void updateBrand(BrandDto brandDetails) throws ResourceNotFoundException {
        BrandDto brandDto = findById(brandDetails.getId());
        brandDto.updateAllFieldsWithoutId(brandDetails);
        brandRepository.save(new Brand(brandDto));
    }

    @Override
    public void delete(BrandDto brandDto) {
        brandRepository.delete(new Brand(brandDto));
    }
}
