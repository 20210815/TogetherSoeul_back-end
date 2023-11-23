package com.example.festival.partner.controller;

import com.example.festival.festival.service.AmazonS3Service;
import com.example.festival.festival.service.UploadFileService;
import com.example.festival.partner.dto.PartnerDto;
import com.example.festival.partner.service.PartnerService;
import com.example.festival.user.dto.UserDto;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {
    private final PartnerService partnerService;
    private final AmazonS3Service amazonS3Service;

    public PartnerController(
            @Autowired PartnerService partnerService,
            @Autowired AmazonS3Service amazonS3Service
            ) {
        this.partnerService = partnerService;
        this.amazonS3Service = amazonS3Service;
    }


    @PostMapping("")
    public String partnerCreate(@RequestParam("partner") String partnerDto, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //현재 로그인한 사용자 정보

        ObjectMapper mapper = new ObjectMapper();
        PartnerDto mapperPartnerDto = mapper.readValue(partnerDto, PartnerDto.class);

        String imageFile = amazonS3Service.saveFile(multipartFile);
        mapperPartnerDto.setImage(imageFile);
        this.partnerService.partnerCreate(authentication.getName(), mapperPartnerDto);

        return "게시글 등록 완료";
    }

    @GetMapping("/{partnerId}")
    public PartnerDto partnerRead(@PathVariable("partnerId") Integer partnerId) {
        return this.partnerService.partnerRead(partnerId);
    }

    @GetMapping("")
    public List<PartnerDto> partnerReadAll() {
        return this.partnerService.partnerReadAll();
    }

    @PatchMapping("/{partnerId}")
    public void partnerUpdate(@PathVariable("partnerId") Integer partnerId, @RequestParam("partner") String partnerDto, @RequestParam("image") MultipartFile multipartFile) throws IOException{

//        if (partnerDto != null) {
//            String imageFile = uploadFileService.storeFile(multipartFile);
//
//            this.partnerService.partnerImageUpdate(partnerId, imageFile);
//        }

        ObjectMapper mapper = new ObjectMapper();
        PartnerDto mapperPartnerDto = mapper.readValue(partnerDto, PartnerDto.class);

        String imageFile = amazonS3Service.saveFile(multipartFile);
        mapperPartnerDto.setImage(imageFile);

        this.partnerService.partnerUpdate(partnerId, mapperPartnerDto);
    }

    @DeleteMapping("/{partnerId}")
    public void partnerDelete(@PathVariable("partnerId") Integer partnerId) {
        this.partnerService.partnerDelete(partnerId);
    }
}
