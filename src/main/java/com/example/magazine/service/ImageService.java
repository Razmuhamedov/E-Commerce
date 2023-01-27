package com.example.magazine.service;

import com.example.magazine.dto.image.ImageDto;
import com.example.magazine.entity.Image;
import com.example.magazine.exception.UnSupportedMediaType;
import com.example.magazine.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${servers.address}")
    private String address;
    private final String fileUrl = "uploads/images/";
    private final ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    private String yearMonthDay(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return String.format("%s/%s/%s", year, month, day);
    }

    public String saveImage(MultipartFile file){
        try {
        String ymd = yearMonthDay();
        String type = file.getContentType().split("/")[1];
        String token = UUID.randomUUID().toString();

        String url = String.format("%s/%s.%s", ymd, token, type);
        File folder = new File(fileUrl+ymd);
            if(!folder.exists()){
                folder.mkdirs();
            }
            Path path = Paths.get(fileUrl).resolve(url);
            Files.copy(file.getInputStream(), path);
            return createImage(yearMonthDay(), type, file.getSize(), token);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Image not created!");
        }
    }

    private String createImage(String yearMonthDay, String type, long size, String token) {
        Image image = new Image();
        image.setCreatedAt(LocalDateTime.now());
        image.setSize(size);
        image.setPath(yearMonthDay);
        image.setToken(token);
        image.setType(type);
        image.setUrl(address + "/image/get/" + token);
        imageRepo.save(image);
        return "Image saved successful";
    }

    public ImageDto convertToDto(Image image, ImageDto dto){
        dto.setId(image.getId());
        dto.setPath(image.getPath());
        dto.setType(image.getType());
        dto.setToken(image.getToken());
        dto.setSize(image.getSize());
        return dto;
    }

    public Image getEntity(String token){
        return imageRepo.findByToken(token).orElseThrow(() -> new IllegalArgumentException("Image not found!"));
    }
    public Image getEntity(Long id){
        return imageRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Image not found!"));
    }

    private Path path(Image image){
        return Paths.get(fileUrl).resolve(String.format("%s/%s.%s", image.getPath(), image.getToken(), image.getType()));
    }

    public Resource load(String token){
        try {
            Image image = getEntity(token);
            Path file = path(image);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            else {
                throw new UnSupportedMediaType("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public byte[] getImage(String token){
        if(token != null && token.length() > 0) {
            try {
                Image image = getEntity(token);
                String path = String.valueOf(path(image));

                byte[] imageBytes;
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(new File(path));
                } catch (IOException e) {
                    return new byte[0];
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                baos.flush();
                imageBytes = baos.toByteArray();
                baos.close();
                return imageBytes;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


}
