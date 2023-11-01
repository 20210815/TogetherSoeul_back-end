package com.example.festival.partner.repository;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.partner.dto.PartnerDto;
import com.example.festival.partner.entity.Partner;
import com.example.festival.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PartnerRepository{
    private final PartnerRepositoryInterface partnerRepositoryInterface;
    private final AuthRepository authRepository;

    public PartnerRepository(@Autowired PartnerRepositoryInterface partnerRepositoryInterface,
                             @Autowired AuthRepository authRepository) {
        this.partnerRepositoryInterface = partnerRepositoryInterface;
        this.authRepository = authRepository;
    }

    public void boardCreate(String identify, PartnerDto partnerDto) {
        User user = authRepository.findByIdentify(identify);

        Partner partner = new Partner();
        BeanUtils.copyProperties(partnerDto, partner);
        partner.setUser(user); //작성자 저장
        this.partnerRepositoryInterface.save(partner);
    }

    public Partner boardRead(Long boardId) {
        Optional<Partner> partner = this.partnerRepositoryInterface.findById(boardId);
        return partner.get();
    }
}
