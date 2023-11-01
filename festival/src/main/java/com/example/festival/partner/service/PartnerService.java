package com.example.festival.partner.service;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.partner.dto.PartnerDto;
import com.example.festival.partner.entity.Partner;
import com.example.festival.partner.repository.PartnerRepository;
import com.example.festival.user.entity.User;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final AuthRepository authRepository;

    public PartnerService(@Autowired PartnerRepository partnerRepository,
                          @Autowired AuthRepository authRepository) {
        this.partnerRepository=partnerRepository;
        this.authRepository = authRepository;
    }

    public void boardCreate(String identify, PartnerDto partnerDto) {
        //User user = authRepository.findByIdentify(identify);
        partnerRepository.boardCreate(identify, partnerDto);
    }

    public PartnerDto boardRead(Long boardId) {
        Partner partner = this.partnerRepository.boardRead(boardId); //읽을 게시물을 찾음
        PartnerDto partnerDto = new PartnerDto();
        BeanUtils.copyProperties(partner, partnerDto);

        partnerDto.setNickname(partner.getUser().getNickname()); // DTO에 user nickname을 넣음
        return partnerDto;
    }

}
