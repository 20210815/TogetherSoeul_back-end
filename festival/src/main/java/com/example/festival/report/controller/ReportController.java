package com.example.festival.report.controller;

import com.example.festival.festival.service.AmazonS3Service;
import com.example.festival.festival.service.UploadFileService;
import com.example.festival.partner.dto.PartnerDto;
import com.example.festival.report.dto.ReportDto;
import com.example.festival.report.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;
    private final AmazonS3Service amazonS3Service;

    public ReportController(
            @Autowired ReportService reportService,
            @Autowired AmazonS3Service amazonS3Service
    ) {
        this.reportService = reportService;
        this.amazonS3Service = amazonS3Service;
    }

    @PostMapping("")
    public String reportCreate(@RequestParam("report") String reportDto, @RequestParam("image")MultipartFile multipartFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ObjectMapper mapper = new ObjectMapper();
        ReportDto mapperReportDto = mapper.readValue(reportDto, ReportDto.class);

        String imageFile = amazonS3Service.saveFile(multipartFile);
        mapperReportDto.setImage(imageFile);


        this.reportService.reportCreate(authentication.getName(), mapperReportDto);
        return "신고 글 작성 완료";
    }

    @GetMapping("/{reportId}")
    public ReportDto reportRead(@PathVariable("reportId")Integer reportId) {
        return this.reportService.reportRead(reportId);
    }

    @GetMapping("")
    public List<ReportDto> reportReadAll() {
        return this.reportService.reportReadAll();
    }

    @PatchMapping("/{reportId}")
    public void reportUpdate(@PathVariable("reportId")Integer reportId, @RequestParam("image") MultipartFile multipartFile, @RequestParam("report") String reportDto) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        ReportDto mapperReportDto = mapper.readValue(reportDto, ReportDto.class);

        String imageFile = amazonS3Service.saveFile(multipartFile);
        mapperReportDto.setImage(imageFile);

        this.reportService.reportUpdate(reportId, mapperReportDto);
    }

    //관리자 사용
    @PatchMapping("/{reportId}/done")
    public void reportUpdateDone(@PathVariable("reportId")Integer reportId) throws IOException{
        this.reportService.reportUpdateDone(reportId);
    }

    @DeleteMapping("/{reportId}")
    public void reportDelete(@PathVariable("reportId")Integer reportId) {
        this.reportService.reportDelete(reportId);
    }
}
