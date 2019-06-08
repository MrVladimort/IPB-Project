package pl.pjatk.ipb.project.control.service.impl;

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
import pl.pjatk.ipb.project.boundary.dto.EmployeeDTO;
import pl.pjatk.ipb.project.boundary.dto.EmployeeInfoDTO;
import pl.pjatk.ipb.project.boundary.dto.RecruitmentDTO;
import pl.pjatk.ipb.project.control.dao.CandidateDAO;
import pl.pjatk.ipb.project.control.dao.EmployeeDAO;
import pl.pjatk.ipb.project.control.dao.HrDAO;
import pl.pjatk.ipb.project.control.dao.JobOfferDAO;
import pl.pjatk.ipb.project.control.dao.RecruitmentDAO;
import pl.pjatk.ipb.project.control.entity.*;
import pl.pjatk.ipb.project.control.entity.EmployeeEntity.EmployeeStatus;
import pl.pjatk.ipb.project.control.entity.RecruitmentEntity.RecruitmentStatus;
import pl.pjatk.ipb.project.controller.exceptions.EntityNotFoundException;
import pl.pjatk.ipb.project.control.mapper.ProjectMapper;
import pl.pjatk.ipb.project.control.service.RecruitmentService;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    @NonNull RecruitmentDAO recruitmentDao;
    @NonNull CandidateDAO candidateDao;
    @NonNull EmployeeDAO employeeDao;
    @NonNull HrDAO hrDao;
    @NonNull JobOfferDAO jobOfferDAO;
    @NonNull PasswordEncoder passwordEncoder;
    @NonNull ProjectMapper mapper;

    @Override
    public RecruitmentDTO createRecruitment(Long candidateId, Long jobOfferId, Double testsResult) {
        log.info(
                "Create recruitment with candidateId: {}, jobOfferId: {}, testsResult: {}",
                candidateId,
                jobOfferId,
                testsResult);
        CandidateEntity candidateEntity =
                candidateDao.findById(candidateId).orElseThrow(EntityNotFoundException::new);
        JobOfferEntity jobOfferEntity =
                jobOfferDAO.findById(jobOfferId).orElseThrow(EntityNotFoundException::new);

        return mapper.recruitmentDtoFromEntity(
                recruitmentDao.save(
                        RecruitmentEntity.builder()
                                .candidate(candidateEntity)
                                .jobOffer(jobOfferEntity)
                                .status(RecruitmentStatus.PENDING)
                                .testResultPercent(testsResult)
                                .build()));
    }

    @Override
    public RecruitmentDTO addCvLink(Long recruitmentId, Long candidateId, String link) {
        RecruitmentEntity recruitmentEntity =
                recruitmentDao.findById(recruitmentId).orElseThrow(EntityNotFoundException::new);
        recruitmentEntity.setLinkToCV(link);
        return mapper.recruitmentDtoFromEntity(recruitmentDao.save(recruitmentEntity));
    }

    @Override
    public RecruitmentDTO refuse(Long recruitmentId, Long hrId) {
        HrEntity hrEntity = hrDao.findById(hrId).orElseThrow(EntityNotFoundException::new);
        RecruitmentEntity recruitmentEntity =
                recruitmentDao
                        .findByIdAndStatus(recruitmentId, RecruitmentStatus.PENDING)
                        .orElseThrow(EntityNotFoundException::new);

        recruitmentEntity.setStatus(RecruitmentStatus.REFUSED);
        recruitmentEntity.setHrManager(hrEntity);
        // TODO Sent info

        return mapper.recruitmentDtoFromEntity(recruitmentDao.save(recruitmentEntity));
    }

    @Override
    public RecruitmentDTO accept(Long recruitmentId, Long hrId) {
        HrEntity hrEntity = hrDao.findById(hrId).orElseThrow(EntityNotFoundException::new);
        RecruitmentEntity recruitmentEntity =
                recruitmentDao
                        .findByIdAndStatus(recruitmentId, RecruitmentStatus.PENDING)
                        .orElseThrow(EntityNotFoundException::new);

        recruitmentEntity.setStatus(RecruitmentStatus.ACCEPTED);
        recruitmentEntity.setHrManager(hrEntity);

        return mapper.recruitmentDtoFromEntity(recruitmentDao.save(recruitmentEntity));
    }

    @Override
    public EmployeeDTO complete(Long recruitmentId, EmployeeInfoDTO employeeInfo, Long candidateId) {
        RecruitmentEntity recruitmentEntity =
                recruitmentDao
                        .findByIdAndStatus(recruitmentId, RecruitmentStatus.ACCEPTED)
                        .orElseThrow(EntityNotFoundException::new);

        recruitmentEntity.setStatus(RecruitmentStatus.COMPLETED);

        CandidateEntity candidateEntity =
                candidateDao.findById(candidateId).orElseThrow(EntityNotFoundException::new);

        EmployeeEntity employeeEntity = mapper.employeeEntityFromCandidate(candidateEntity);
        employeeEntity.setPesel(employeeInfo.getPesel());
        employeeEntity.setNip(employeeInfo.getNip());
        employeeEntity.setAddress(employeeInfo.getAddress());
        employeeEntity.setEmployeeIdentifier(UUID.randomUUID().toString());
        employeeEntity.setStatus(EmployeeStatus.PENDING);
        employeeEntity.setRole(UserEntity.UserRole.EMPLOYEE);

        return mapper.employeeDtoFromEntity(employeeDao.save(employeeEntity));
    }

    @Override
    public List<RecruitmentDTO> getAllRecruitments() {
        return StreamSupport.stream(recruitmentDao.findAll().spliterator(), false)
                .map(mapper::recruitmentDtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentDTO> getAllRecruitmentsForCandidate(Long id) {
        return recruitmentDao.findAllByCandidate_Id(id).stream()
                .map(mapper::recruitmentDtoFromEntity)
                .collect(Collectors.toList());
    }

    @Bean
    public void loadHrData() {
        hrDao.save(
                HrEntity.builder()
                        .email("hr@mail.com")
                        .name("Hrka")
                        .surname("Hrkowska")
                        .password(passwordEncoder.encode("pass"))
                        .employeeIdentifier(UUID.randomUUID().toString())
                        .address("Tutaj")
                        .nip("12345")
                        .pesel("12345")
                        .status(EmployeeStatus.WORKING)
                        .phone("+41")
                        .build());

        hrDao.save(
                HrEntity.builder()
                        .email("hr2@mail.com")
                        .name("Hrka 2")
                        .surname("Markowska")
                        .password(passwordEncoder.encode("pass"))
                        .employeeIdentifier(UUID.randomUUID().toString())
                        .address("Tutaj")
                        .nip("123456")
                        .pesel("123456")
                        .status(EmployeeStatus.WORKING)
                        .phone("+41")
                        .build());

        hrDao.save(
                HrEntity.builder()
                        .email("hr3@mail.com")
                        .name("Hrka 3")
                        .surname("Gegowara")
                        .password(passwordEncoder.encode("pass"))
                        .employeeIdentifier(UUID.randomUUID().toString())
                        .address("Tutaj")
                        .nip("1234567")
                        .pesel("1234567")
                        .status(EmployeeStatus.WORKING)
                        .phone("+41")
                        .build());
    }
}
