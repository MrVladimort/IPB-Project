package pl.pjatk.ipb.project.control.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.pjatk.ipb.project.boundary.dto.CandidateDTO;
import pl.pjatk.ipb.project.boundary.dto.EmployeeDTO;
import pl.pjatk.ipb.project.boundary.dto.HrDTO;
import pl.pjatk.ipb.project.boundary.dto.InterviewDTO;
import pl.pjatk.ipb.project.boundary.dto.JobOfferDTO;
import pl.pjatk.ipb.project.boundary.dto.RecruitmentDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterCandidateDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterHrDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterTeamLeadDTO;
import pl.pjatk.ipb.project.boundary.dto.TeamLeadDTO;
import pl.pjatk.ipb.project.boundary.dto.TestDTO;
import pl.pjatk.ipb.project.boundary.dto.UserDTO;
import pl.pjatk.ipb.project.control.entity.CandidateEntity;
import pl.pjatk.ipb.project.control.entity.EmployeeEntity;
import pl.pjatk.ipb.project.control.entity.HrEntity;
import pl.pjatk.ipb.project.control.entity.InterviewEntity;
import pl.pjatk.ipb.project.control.entity.JobOfferEntity;
import pl.pjatk.ipb.project.control.entity.RecruitmentEntity;
import pl.pjatk.ipb.project.control.entity.TeamLeadEntity;
import pl.pjatk.ipb.project.control.entity.TestEntity;
import pl.pjatk.ipb.project.control.entity.UserEntity;
import pl.pjatk.ipb.project.control.security.UserPrincipal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProjectMapper {

  public abstract CandidateEntity candidateEntityFromDto(RegisterCandidateDTO dto);

  public abstract CandidateDTO candidateDtoFromEntity(CandidateEntity entity);

  public abstract UserDTO userDtoFromPrincipal(UserPrincipal entity);

  public abstract UserPrincipal principalFromEntity(UserEntity entity);

  public abstract UserDTO userDtoFromEntity(UserEntity entity);

  public abstract TestDTO testDtoFromEntity(TestEntity entity);

  public abstract TestEntity testEntityFromDto(TestDTO entity);

  public abstract JobOfferDTO jobOfferEntityFromDto(JobOfferEntity entity);

  @Mapping(target = "candidate", source = "recruitment.candidate")
  public abstract InterviewDTO interviewEntityToDto(InterviewEntity entity);

  public abstract RecruitmentDTO recruitmentDtoFromEntity(RecruitmentEntity entity);

  public abstract EmployeeDTO employeeDtoFromEntity(EmployeeEntity entity);

  public abstract EmployeeEntity employeeEntityFromCandidate(CandidateEntity entity);

  public abstract HrDTO hrDtoFromEntity(HrEntity entity);

  public abstract HrEntity hrEntityFromDto(HrDTO hrDTO);

  public abstract HrEntity hrEntityFromDto(RegisterHrDTO hrDTO);

  public abstract TeamLeadDTO teamLeadDtoFromEntity(TeamLeadEntity entity);

  public abstract TeamLeadEntity teamLeadEntityFromDto(TeamLeadDTO dto);

  public abstract TeamLeadEntity teamLeadEntityFromDto(RegisterTeamLeadDTO dto);

}
