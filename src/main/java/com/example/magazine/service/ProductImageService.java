package com.example.magazine.service;

import com.example.magazine.dto.image.ImageDto;
import com.example.magazine.entity.ProductImage;
import com.example.magazine.repository.ProductImageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageService {
    private final ProductImageRepo productImageRepo;
    private final ImageService imageService;

    public ProductImageService(ProductImageRepo productImageRepo, ImageService imageService) {
        this.productImageRepo = productImageRepo;
        this.imageService = imageService;
    }

    public void createProdImage(Long productId, Long imageId) {
        ProductImage productImage = new ProductImage();
        productImage.setProductId(productId);
        productImage.setImageId(imageId);
        productImageRepo.save(productImage);
    }

    public String setImagesToProduct(Long productId, List<ProductImage> imageList){
        imageList.forEach(prodImage -> createProdImage(productId, prodImage.getImageId()));
        return "Images saved to Product!";
    }

    public List<ImageDto> getAllByProduct(Long id) {
        List<ProductImage> productImages = productImageRepo.findAllByProductId(id);
        return productImages.stream().map(image -> imageService.convertToDto(imageService.getEntity(id), new ImageDto())).collect(Collectors.toList());
    }

    public String deleteProdImages(Long id){
        productImageRepo.deleteAllByProductId(id);
        return "All images was deleted!";
    }
}
