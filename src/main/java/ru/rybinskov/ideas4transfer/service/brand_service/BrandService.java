package ru.rybinskov.ideas4transfer.service.brand_service;


import ru.rybinskov.ideas4transfer.domain.Brand;

public interface BrandService {

    Brand getBrandByAbbr(String abbr);
}
