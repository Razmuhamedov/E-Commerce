package com.example.magazine.service;

import com.example.magazine.dto.product.ProductCreateDto;
import com.example.magazine.dto.product.ProductDto;
import com.example.magazine.entity.Product;
import com.example.magazine.exception.BadRequest;
import com.example.magazine.repository.ProductRepo;
import com.example.magazine.type.ProductStatus;
import com.example.magazine.type.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductImageService productImageService;

    public ProductService(ProductRepo productRepo, ProductImageService productImageService) {
        this.productRepo = productRepo;
        this.productImageService = productImageService;
    }

    public String createProduct(ProductCreateDto dto) {
        dto.setRate(0.0);
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.now());
        product.setStatus(ProductStatus.CREATED);
        product.setVisible(true);
        convertToEntity(dto, product);
        productRepo.save(product);
        return "Product created successful!";
    }

    public String updateProduct(Long id, ProductCreateDto dto) {
        Product product = getEntityForDto(id);
        product.setUpdatedAt(LocalDateTime.now());
        convertToEntity(dto, product);
        productRepo.save(product);
        return "Product updated successful!";
    }

    public String deleteProduct(Long id) {
        Product product = getEntity(id);
        productRepo.delete(product);
        productImageService.deleteProdImages(id);
        return "Product deleted from database!";
    }

    public String softDelete(Long id) {
        Product product = getEntityForDto(id);
        product.setDeletedAt(LocalDateTime.now());
        product.setVisible(false);
        product.setStatus(ProductStatus.BLOCKED);
        productRepo.save(product);
        return "Product deleted!";
    }

    public String changeStatus(Long id, String status) {
        Product product = getEntity(id);
        product.setStatus(ProductStatus.valueOf(status));
        productRepo.save(product);
        return "Product status changed to " + status;
    }

    public Long countByType(String type) {
        return productRepo.countByProductTypeAndDeletedAtIsNull(ProductType.valueOf(type));
    }

    public List<ProductDto> getAll(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Product> pages = productRepo.getAllByDeletedAt(request);
        return pages.stream().map(product -> (convertToDto(product, new ProductDto()))).collect(Collectors.toList());
    }

    public ProductDto getById(Long id) {
        ProductDto dto = convertToDto(getEntityForDto(id), new ProductDto());
        dto.setImageList(productImageService.getAllByProduct(id));
        return dto;
    }

    public Product getEntityForDto(Long id){
        Optional<Product> optional = productRepo.findByIdAndVisibleIsTrue(id);
        if(optional.isEmpty()){
            throw new BadRequest("Product not found!");
        }
        return optional.get();
    }

    private Product getEntity(Long id){
        Optional<Product> optional = productRepo.findById(id);
        if(optional.isEmpty()){
            throw new BadRequest("Product not found!");
        }
        return optional.get();
    }

    private ProductDto convertToDto(Product product, ProductDto dto){
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setStatus(product.getStatus());
        dto.setPrice(product.getPrice());
        dto.setRate(product.getRate());
        dto.setDescription(product.getDescription());
        dto.setProductType(product.getProductType());
        dto.setImageList(productImageService.getAllByProduct(product.getId()));
        return dto;
    }

    private void convertToEntity(ProductCreateDto dto, Product product){
        product.setProductType(dto.getProductType());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setRate(dto.getRate());
    }
}
