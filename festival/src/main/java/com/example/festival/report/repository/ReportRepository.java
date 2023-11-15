package com.example.festival.report.repository;

import com.example.festival.auth.repository.AuthRepository;
import com.example.festival.festival.entity.Festival;
import com.example.festival.festival.repository.FestivalRepository;
import com.example.festival.report.dto.ReportDto;
import com.example.festival.report.entity.Report;
import com.example.festival.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ReportRepository {
    private final ReportRepositoryInterface reportRepositoryInterface;
    private final FestivalRepository festivalRepository;
    private final AuthRepository authRepository;
    public ReportRepository(
            @Autowired ReportRepositoryInterface reportRepositoryInterface,
            @Autowired AuthRepository authRepository,
            @Autowired FestivalRepository festivalRepository
    ){
        this.reportRepositoryInterface = reportRepositoryInterface;
        this.authRepository = authRepository;
        this.festivalRepository = festivalRepository;
    }

    public void reportCreate(String identify, ReportDto reportDto) {
        Report report = new Report();
        Festival festival = this.festivalRepository.findByFestivalId(reportDto.getFestivalId()).get();
        BeanUtils.copyProperties(reportDto, report);

        User user = this.authRepository.findByIdentify(identify);
        report.setUser(user);
        report.setDone(false);
        report.setFestival(festival);

        this.reportRepositoryInterface.save(report);
    }

    public ReportDto reportRead(Integer reportId) {
        Report report = this.reportRepositoryInterface.findById(reportId).get();

        ReportDto reportDto = new ReportDto();

        BeanUtils.copyProperties(report, reportDto);
        reportDto.setNickname(report.getUser().getNickname());
        reportDto.setAddress(report.getUser().getAddress());
        reportDto.setFestivalId(report.getFestival().getFestivalId());

        return reportDto;
    }

    public List<ReportDto> reportReadAll() {
        Iterator<Report> iterator = this.reportRepositoryInterface.findAll().iterator();

        List<ReportDto> reportDtos = new ArrayList<>();

        while(iterator.hasNext()) {
            ReportDto reportDto = new ReportDto();
            reportDto = reportRead(iterator.next().getReportId());
            reportDtos.add(reportDto);
        }
        return reportDtos;
    }

    //제목, 내용 수정
    public void reportUpdate(Integer reportId, ReportDto reportDto) {
        Report report = this.reportRepositoryInterface.findById(reportId).get();

        if(reportDto.getTitle() != null) {
            report.setTitle(reportDto.getTitle());
            report.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        if(reportDto.getContent() != null) {
            report.setContent(reportDto.getContent());
            report.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        if(reportDto.getImage() != null) {
            report.setImage(reportDto.getImage());
            report.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        }
        this.reportRepositoryInterface.save(report);
    }

    // 조치완료
    public void reportUpdateDone(Integer reportId) {
        Report report = this.reportRepositoryInterface.findById(reportId).get();

        System.out.println(report.getDone());

        if(report.getDone()) { //들어온 거랑 상관없이 그냥 토글로
            report.setDone(false);
        }
        else {
            report.setDone(true);
        }
        this.reportRepositoryInterface.save(report);
    }

    public void reportDelete(Integer reportId) {
        this.reportRepositoryInterface.deleteById(reportId);
    }

}
