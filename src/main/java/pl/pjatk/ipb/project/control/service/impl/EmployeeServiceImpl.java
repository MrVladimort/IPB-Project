package pl.pjatk.ipb.project.control.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pjatk.ipb.project.boundary.dto.EmployeeDTO;
import pl.pjatk.ipb.project.boundary.dto.HrDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterHrDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterTeamLeadDTO;
import pl.pjatk.ipb.project.boundary.dto.TeamLeadDTO;
import pl.pjatk.ipb.project.control.dao.EmployeeDAO;
import pl.pjatk.ipb.project.control.dao.HrDAO;
import pl.pjatk.ipb.project.control.dao.TeamLeadDAO;
import pl.pjatk.ipb.project.control.entity.EmployeeEntity;
import pl.pjatk.ipb.project.control.entity.EmployeeEntity.EmployeeStatus;
import pl.pjatk.ipb.project.control.entity.HrEntity;
import pl.pjatk.ipb.project.control.entity.TeamLeadEntity;
import pl.pjatk.ipb.project.controller.exceptions.EntityNotFoundException;
import pl.pjatk.ipb.project.control.mapper.ProjectMapper;
import pl.pjatk.ipb.project.control.security.JwtTokenProvider;
import pl.pjatk.ipb.project.control.service.EmployeeService;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  @NonNull HrDAO hrDao;
  @NonNull TeamLeadDAO teamLeadDao;
  @NonNull EmployeeDAO employeeDao;
  @NonNull ProjectMapper mapper;
  @NonNull JwtTokenProvider tokenProvider;
  @NonNull PasswordEncoder passwordEncoder;

  @Override
  public HrDTO createHrManager(RegisterHrDTO registerHrDTO) {
    log.info("Register data: {}", registerHrDTO);
    HrEntity hrEntity = mapper.hrEntityFromDto(registerHrDTO);
    hrEntity.setPassword(passwordEncoder.encode(hrEntity.getPassword()));

    hrEntity = hrDao.save(hrEntity);

    String jwt = tokenProvider.generateToken(mapper.principalFromEntity(hrEntity));

    HrDTO candidateDto = mapper.hrDtoFromEntity(hrEntity);
    candidateDto.setAccessToken(jwt);

    return candidateDto;
  }

  @Override
  public TeamLeadDTO createTeamLead(RegisterTeamLeadDTO registerTeamLeadDTO) {
    log.info("Register data: {}", registerTeamLeadDTO);
    TeamLeadEntity teamLeadEntity = mapper.teamLeadEntityFromDto(registerTeamLeadDTO);
    teamLeadEntity.setPassword(passwordEncoder.encode(teamLeadEntity.getPassword()));
    teamLeadEntity = teamLeadDao.save(teamLeadEntity);

    String jwt = tokenProvider.generateToken(mapper.principalFromEntity(teamLeadEntity));

    TeamLeadDTO candidateDto = mapper.teamLeadDtoFromEntity(teamLeadEntity);
    candidateDto.setAccessToken(jwt);

    return candidateDto;
  }

  @Override
  public EmployeeDTO activateEmployee(Long employeeId) {
    EmployeeEntity employeeEntity =
        employeeDao.findById(employeeId).orElseThrow(EntityNotFoundException::new);
    employeeEntity.setStatus(EmployeeStatus.WORKING);
    return mapper.employeeDtoFromEntity(employeeDao.save(employeeEntity));
  }

  @Override
  public List<EmployeeDTO> getAllEmployees() {
    return StreamSupport.stream(employeeDao.findAll().spliterator(), false)
        .map(mapper::employeeDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<HrDTO> getAllHrs() {
    return StreamSupport.stream(hrDao.findAll().spliterator(), false)
            .map(mapper::hrDtoFromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public List<TeamLeadDTO> getAllTeamLeads() {
    return StreamSupport.stream(teamLeadDao.findAll().spliterator(), false)
            .map(mapper::teamLeadDtoFromEntity)
            .collect(Collectors.toList());
  }
}
