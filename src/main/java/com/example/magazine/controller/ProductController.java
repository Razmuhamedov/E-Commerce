package com.example.magazine.controller;

import com.example.magazine.dto.product.ProductCreateDto;
import com.example.magazine.dto.product.ProductDto;
import com.example.magazine.entity.ProductImage;
import com.example.magazine.service.ProductImageService;
import com.example.magazine.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }

//////////////////////////*ADMIN, MODERATOR*/////////////////////////////////////

    @PostMapping("/secured/create")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreateDto dto){
        String result = productService.createProduct(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/secured/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody @Valid ProductCreateDto dto){
        String result = productService.updateProduct(id ,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/secured/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        String result = productService.deleteProduct(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secured/delete/{id}")
    public ResponseEntity<?> softDeleteProduct(@PathVariable("id") Long id){
        String result = productService.softDelete(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/secured/{id}/status/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id,
                                          @PathVariable("status") String status){
        String result = productService.changeStatus(id, status);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secured/count/{type}")
    public ResponseEntity<?> countByType(@PathVariable("type") String type){
        Long result = productService.countByType(type);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/secured/image/set/{id}")
    public ResponseEntity<?> setImages(@PathVariable("id") Long id,
                                       @RequestBody List<ProductImage> imageList){
        String result = productImageService.setImagesToProduct(id, imageList);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/secured/image/delete/{id}")
    public ResponseEntity<?> deleteImages(@PathVariable("id") Long id){
        String result = productImageService.deleteProdImages(id);
        return ResponseEntity.ok(result);
    }

    //////////////////////////////*USER*//////////////////////////////////////////

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(@RequestParam("page") int page,
                                            @RequestParam("size") int size){
        List<ProductDto> result = productService.getAll(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProdictById(@PathVariable("id") Long id){
        ProductDto result = productService.getById(id);
        return ResponseEntity.ok(result);
    }


}
