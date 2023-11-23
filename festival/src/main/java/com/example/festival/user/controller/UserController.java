package com.example.festival.user.controller;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.festival.dto.FestivalDTO;
import com.example.festival.festival.service.AmazonS3Service;
import com.example.festival.user.dto.UserDto;
import com.example.festival.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.festival.festival.service.UploadFileService;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AmazonS3Service amazonS3Service;

    public UserController(
            @Autowired UserService userService,
            @Autowired AmazonS3Service amazonS3Service
    ) {
        this.userService = userService;
        this.amazonS3Service = amazonS3Service;
    }

    @GetMapping("/mypage")
    public UserDto mypage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //현재 로그인한 사용자 정보
        System.out.println("로그인 이름이?: " + authentication.getName());
        return this.userService.readUser(authentication.getName()); //getName이 identify
    }

    @PatchMapping("/update")
    public void update(@RequestParam("user") String userDto, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //현재 로그인한 사용자 정보

        ObjectMapper mapper = new ObjectMapper();
        UserDto mapperUserDto = mapper.readValue(userDto, UserDto.class);

        String imageFile = amazonS3Service.saveFile(multipartFile);
        mapperUserDto.setImage(imageFile);

//        String festival = userService.uploadFestival(mapperFestivalDTO);

        this.userService.updateUser(authentication.getName(), mapperUserDto);
    }
}
