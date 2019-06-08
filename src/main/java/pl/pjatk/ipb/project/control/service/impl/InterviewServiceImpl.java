package pl.pjatk.ipb.project.control.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pjatk.ipb.project.boundary.dto.InterviewDTO;
import pl.pjatk.ipb.project.control.dao.InterviewDAO;
import pl.pjatk.ipb.project.control.dao.RecruitmentDAO;
import pl.pjatk.ipb.project.control.dao.TeamLeadDAO;
import pl.pjatk.ipb.project.control.entity.EmployeeEntity.EmployeeStatus;
import pl.pjatk.ipb.project.control.entity.InterviewEntity;
import pl.pjatk.ipb.project.control.entity.InterviewEntity.InterviewStatus;
import pl.pjatk.ipb.project.control.entity.RecruitmentEntity;
import pl.pjatk.ipb.project.control.entity.RecruitmentEntity.RecruitmentStatus;
import pl.pjatk.ipb.project.control.entity.TeamLeadEntity;
import pl.pjatk.ipb.project.control.exceptions.EntityNotFoundException;
import pl.pjatk.ipb.project.control.mapper.ProjectMapper;
import pl.pjatk.ipb.project.control.service.InterviewService;

@Service
@Slf4j
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {
    @NonNull InterviewDAO interviewDao;
    @NonNull TeamLeadDAO teamLeadDao;
    @NonNull PasswordEncoder passwordEncoder;
    @NonNull RecruitmentDAO recruitmentDao;
    @NonNull ProjectMapper mapper;

    @Override
    public InterviewDTO getInterviewByRecruitmentId(Long recruitmentId) {
        InterviewEntity interviewEntity = interviewDao.findByRecruitment_Id(recruitmentId).orElseThrow(EntityNotFoundException::new);
        log.info("Interview: {}", interviewEntity);

        return mapper.interviewEntityToDto(interviewEntity);
    }

    @Override
    public List<InterviewDTO> getAllInterviews() {
        List<InterviewEntity> interviewEntities = StreamSupport.stream(interviewDao.findAll().spliterator(), false).collect(Collectors.toList());
        log.info("Interviews: {}", interviewEntities);

        return interviewEntities.stream()
                .map(mapper::interviewEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewDTO> getAllInterviewByTeamLeadId(Long teamLeadId) {
        List<InterviewEntity> interviewEntities = interviewDao.findAllByTeamLead_Id(teamLeadId);
        log.info("Interviews: {}", interviewEntities);

        return interviewEntities.stream()
                .map(mapper::interviewEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewDTO> getAllInterviewByCandidateId(Long candidateId) {
        List<InterviewEntity> interviewEntities = interviewDao.findAllByRecruitment_Candidate_Id(candidateId);
        log.info("Interviews: {}", interviewEntities);

        return interviewEntities.stream()
                .map(mapper::interviewEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewDTO> getAllInterviewByDate(LocalDate date) {
        List<InterviewEntity> interviewEntities = interviewDao.findAllByDate(date);
        log.info("Interviews: {}", interviewEntities);

        return interviewEntities.stream()
                .map(mapper::interviewEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InterviewDTO createInterview(
            Long candidateId, Long recruitmentId, InterviewDTO dateAndTime) {
        log.info("Create interview with params candidateId: {}, recruitmentId: {}, dateAndTime: {}", candidateId, recruitmentId, dateAndTime);

        RecruitmentEntity recruitmentEntity =
                recruitmentDao
                        .findByIdAndStatus(recruitmentId, RecruitmentStatus.ACCEPTED)
                        .orElseThrow(EntityNotFoundException::new);

        log.info("Recruitment: {}", recruitmentEntity);

        InterviewEntity interview = interviewDao.save(
                InterviewEntity.builder()
                        .date(dateAndTime.getDate())
                        .time(dateAndTime.getTime())
                        .status(InterviewStatus.PENDING)
                        .recruitment(recruitmentEntity)
                        .build());

        log.info("Interview: {}", interview);

        return mapper.interviewEntityToDto(interview);
    }

    @Override
    public InterviewDTO acceptInterview(Long interviewId, Long teamLeadId) {
        InterviewEntity interviewEntity =
                interviewDao.findById(interviewId).orElseThrow(EntityNotFoundException::new);
        TeamLeadEntity teamLeadEntity =
                teamLeadDao.findById(teamLeadId).orElseThrow(EntityNotFoundException::new);

        interviewEntity.setTeamLead(teamLeadEntity);
        interviewEntity.setStatus(InterviewStatus.ACCEPTED);

        return mapper.interviewEntityToDto(interviewDao.save(interviewEntity));
    }

    @Override
    public InterviewDTO refuse(Long interviewId) {
        InterviewEntity interviewEntity = interviewDao.findById(interviewId).orElseThrow(EntityNotFoundException::new);

        interviewEntity.setStatus(InterviewStatus.REFUSED);

        return mapper.interviewEntityToDto(interviewDao.save(interviewEntity));
    }

    @Override
    public InterviewDTO complete(Long interviewId, String opinion) {
        InterviewEntity interviewEntity = interviewDao.findById(interviewId).orElseThrow(EntityNotFoundException::new);

        interviewEntity.setStatus(InterviewStatus.COMPLETED);
        interviewEntity.setOpinion(opinion);

        return mapper.interviewEntityToDto(interviewDao.save(interviewEntity));
    }

    @Bean
    public void loadTeamLeadData() {
        teamLeadDao.save(
                TeamLeadEntity.builder()
                        .email("tl@mail.com")
                        .name("TeamLead")
                        .surname("Keralski")
                        .password(passwordEncoder.encode("pass"))
                        .employeeIdentifier(UUID.randomUUID().toString())
                        .address("Tutaj")
                        .nip("12")
                        .pesel("12")
                        .status(EmployeeStatus.WORKING)
                        .build());

        teamLeadDao.save(
                TeamLeadEntity.builder()
                        .email("tl2@mail.com")
                        .name("TeamLead2")
                        .surname("Kowalski")
                        .password(passwordEncoder.encode("pass"))
                        .employeeIdentifier(UUID.randomUUID().toString())
                        .address("Tutaj")
                        .nip("123")
                        .pesel("123")
                        .status(EmployeeStatus.WORKING)
                        .build());

        teamLeadDao.save(
                TeamLeadEntity.builder()
                        .email("tl3@mail.com")
                        .name("TeamLead3")
                        .surname("TL")
                        .password(passwordEncoder.encode("pass"))
                        .employeeIdentifier(UUID.randomUUID().toString())
                        .address("Tutaj")
                        .nip("1234")
                        .pesel("1234")
                        .status(EmployeeStatus.WORKING)
                        .build());
    }
}
