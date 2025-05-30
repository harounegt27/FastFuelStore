package com.pfe.ffs.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {

    private Long id;

    private Long rating;

    private String description;

    private Long userId;


    private Long produitId;

    private String userName;

}
