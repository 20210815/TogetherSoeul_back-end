package com.example.festival.partner.controller;

import com.example.festival.partner.dto.PartnerDto;
import com.example.festival.partner.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {
    private final PartnerService partnerService;

    public PartnerController(
            @Autowired PartnerService partnerService
    ) {
        this.partnerService = partnerService;
    }

    @PostMapping("/create")
    public void boardCreate(@RequestBody PartnerDto partnerDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //현재 로그인한 사용자 정보
        this.partnerService.boardCreate(authentication.getName(), partnerDto);
    }

    @GetMapping("/{boardId}")
    public PartnerDto boardRead(@PathVariable("boardId") Long boardId) {
        return this.partnerService.boardRead(boardId);
    }

    @GetMapping("/readAll")
    public List<PartnerDto> boardReadAll() {
        return this.partnerService.boardReadAll();
    }

    @PatchMapping("/{boardId}")
    public void boardUpdate(@PathVariable("boardId") Long boardId, @RequestBody PartnerDto partnerDto) {
        this.partnerService.boardUpdate(boardId, partnerDto);
    }

    @DeleteMapping("/{boardId}")
    public void boardDelete(@PathVariable("boardId") Long boardId) {
        this.partnerService.boardDelete(boardId);
    }
}
