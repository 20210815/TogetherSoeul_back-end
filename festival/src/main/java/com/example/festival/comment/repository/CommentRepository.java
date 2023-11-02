package com.example.festival.comment.repository;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.comment.dto.CommentDto;
import com.example.festival.comment.entity.Comment;
import com.example.festival.partner.entity.Partner;
import com.example.festival.partner.repository.PartnerRepositoryInterface;
import com.example.festival.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {
    private final CommentRepositoryInterface commentRepositoryInterface;
    private final AuthRepository authRepository;
    private final PartnerRepositoryInterface partnerRepositoryInterface;

    public CommentRepository(@Autowired CommentRepositoryInterface commentRepositoryInterface,
                             @Autowired AuthRepository authRepository,
                             @Autowired PartnerRepositoryInterface partnerRepositoryInterface) {
        this.commentRepositoryInterface = commentRepositoryInterface;
        this.authRepository = authRepository;
        this.partnerRepositoryInterface = partnerRepositoryInterface;
    }

    public void commentCreate(String identify, Long partnerId,CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        User user = authRepository.findByIdentify(identify);
        Partner partner = partnerRepositoryInterface.findById(partnerId).get();

        comment.setPartner(partner);
        comment.setUser(user);

        this.commentRepositoryInterface.save(comment);

    }

    public List<CommentDto> commentReadAllByPartner(Long partnerId) {
        List<Comment> comments = this.commentRepositoryInterface.findAllByPartner_PartnerId(partnerId);

        List<CommentDto> commentDtos = new ArrayList<>();
        for(Comment comment: comments) {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            //System.out.println("CommentDtos 과정: " + commentDto);
            commentDto.setNickname(comment.getUser().getNickname());
            commentDto.setPartnerId(comment.getCommentId());
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

}
