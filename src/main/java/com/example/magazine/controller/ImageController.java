package com.example.magazine.controller;

import com.example.magazine.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveImage(@RequestParam("file") MultipartFile file){
        String result = imageService.saveImage(file);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/load/{filename:.+}")
    public ResponseEntity<?> saveFile(@PathVariable String filename){
        Resource result = imageService.load(filename);
        return ResponseEntity.ok().header("Content-Disposition",
                "attachment; filename=" + "image.png").body(result);
    }

    @GetMapping(value = "/get/{link:.+}", produces = MediaType.IMAGE_PNG_VALUE)
    public
    @ResponseBody
    byte[] getImage(@PathVariable("link") String link){
        return imageService.getImage(link);
    }


}
