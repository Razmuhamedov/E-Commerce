package com.example.magazine.service;

import com.example.magazine.dto.rate.RateCreateDto;
import com.example.magazine.dto.rate.RateDto;
import com.example.magazine.entity.Product;
import com.example.magazine.entity.Rate;
import com.example.magazine.exception.BadRequest;
import com.example.magazine.repository.ProductRepo;
import com.example.magazine.repository.RateRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateService {
    private final RateRepo rateRepo;
    private final ProfileService profileService;
    private final ProductService productService;

    public RateService(RateRepo rateRepo, ProfileService profileService, ProductService productService) {
        this.rateRepo = rateRepo;
        this.profileService = profileService;
        this.productService = productService;
    }


    public Double create(RateCreateDto dto) {
        Rate rate;
        Optional<Rate> optional = rateRepo.findByProfileIdAndProductId(dto.getProfileId(), dto.getProductId());
        if(optional.isPresent()){
            rate = optional.get();
            rate.setRate(dto.getRate());
            rate.setUpdatedAt(LocalDateTime.now());
        }
        else {
            rate = new Rate();
            rate.setProductId(dto.getProductId());
            rate.setProfileId(dto.getProfileId());
            rate.setRate(dto.getRate());
            rate.setCreatedAt(LocalDateTime.now());
        }
        rateRepo.save(rate);
        Product product = productService.getEntityForDto(dto.getProductId());
        product.setRate(getProductRate(dto.getProductId()));
        return product.getRate();
    }

        public List<RateDto> getByProfileId(Long id, int page, int size) {
            profileService.getProfileDto(id);
            PageRequest request = PageRequest.of(page, size);
            Page<Rate> pages = rateRepo.findAllByProfileId(id, request);
            return pages.stream().map(rate -> (convertToDto(rate, new RateDto()))).collect(Collectors.toList());
        }

    public List<RateDto> getByProductId(Long id, int page, int size) {
        productService.getEntityForDto(id);
        PageRequest request = PageRequest.of(page, size);
        Page<Rate> pages = rateRepo.findAllByProductId(id, request);
        return pages.stream().map(rate -> (convertToDto(rate, new RateDto()))).collect(Collectors.toList());
    }

    public List<RateDto> getAll(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Rate> pages = rateRepo.findAll(request);
        return pages.stream().map(rate -> (convertToDto(rate, new RateDto()))).collect(Collectors.toList());
    }

    public String delete(Long id) {
        Rate rate = getEntity(id);
        rateRepo.delete(rate);
        return "Rate deleted!";
    }

    public RateDto getById(Long id) {
        return convertToDto(getEntity(id), new RateDto());
    }

    public Double getProductRate(Long id){
        return rateRepo.getProductRate(id);
    }

    private Rate getEntity(Long id){
        Optional<Rate> optional = rateRepo.findById(id);
        if(optional.isEmpty()){
            throw new BadRequest("Rate not found!");
        }
        return optional.get();
    }

    private RateDto convertToDto(Rate rate, RateDto dto){
        dto.setId(rate.getId());
        dto.setRate(rate.getRate());
        dto.setProduct(rate.getProduct());
        dto.setProfile(rate.getProfile());
        return dto;
    }
}
