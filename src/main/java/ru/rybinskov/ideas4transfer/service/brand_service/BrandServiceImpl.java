package ru.rybinskov.ideas4transfer.service.brand_service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.repository.BrandRepository;

public class BrandServiceImpl implements BrandService{

   private BrandRepository brandRepository;

   @Autowired
   public void setBrandRepository(BrandRepository brandRepository) {
       this.brandRepository = brandRepository;
   }

    @Override
    public Brand getBrandByAbbr(String abbr) {
        return brandRepository.findBrandByAbbr(abbr);
    }


}
