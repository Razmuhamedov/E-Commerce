package com.example.magazine.dto.image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDto {
    private Long id;
    private String path;
    private String type;
    private Long size;
    private String token;
    private String url;
}
