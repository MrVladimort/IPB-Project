package pl.pjatk.ipb.project.control.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pjatk.ipb.project.boundary.dto.CandidateDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterCandidateDTO;
import pl.pjatk.ipb.project.control.dao.CandidateDAO;
import pl.pjatk.ipb.project.control.entity.CandidateEntity;
import pl.pjatk.ipb.project.control.entity.UserEntity;
import pl.pjatk.ipb.project.control.mapper.ProjectMapper;
import pl.pjatk.ipb.project.control.security.JwtTokenProvider;
import pl.pjatk.ipb.project.control.service.CandidateService;
import pl.pjatk.ipb.project.control.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    @NonNull private CandidateDAO candidateDao;
    @NonNull PasswordEncoder passwordEncoder;
    @NonNull JwtTokenProvider tokenProvider;
    @NonNull private ProjectMapper mapper;

    @Override
    public CandidateDTO registerCandidate(RegisterCandidateDTO registerCandidateDto) {
        log.info("Register data: {}", registerCandidateDto);
        CandidateEntity candidateEntity = mapper.candidateEntityFromDto(registerCandidateDto);
        candidateEntity.setPassword(passwordEncoder.encode(candidateEntity.getPassword()));
        candidateEntity.setRole(UserEntity.UserRole.CANDIDATE);

        candidateEntity = candidateDao.save(candidateEntity);

        String jwt = tokenProvider.generateToken(mapper.principalFromEntity(candidateEntity));

        CandidateDTO candidateDto = mapper.candidateDtoFromEntity(candidateEntity);
        candidateDto.setAccessToken(jwt);

        return candidateDto;
    }

    @Override
    public List<CandidateDTO> getAllCandidates() {
        return StreamSupport.stream(candidateDao.findAll().spliterator(), false)
                .map(mapper::candidateDtoFromEntity)
                .collect(Collectors.toList());
    }
}
