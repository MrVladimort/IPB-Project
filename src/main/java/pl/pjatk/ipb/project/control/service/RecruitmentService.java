package pl.pjatk.ipb.project.control.service;

import java.util.List;
import pl.pjatk.ipb.project.boundary.dto.EmployeeDTO;
import pl.pjatk.ipb.project.boundary.dto.EmployeeInfoDTO;
import pl.pjatk.ipb.project.boundary.dto.RecruitmentDTO;
import pl.pjatk.ipb.project.control.security.UserPrincipal;

public interface RecruitmentService {
  RecruitmentDTO createRecruitment(Long candidateId, Long jobOfferId, Double testsResult);

  RecruitmentDTO addCvLink(Long recruitmentId, Long candidateId, String link);

  RecruitmentDTO refuse(Long recruitmentId, Long HrId);

  RecruitmentDTO accept(Long recruitmentId, Long HrId);

  EmployeeDTO complete(Long recruitmentId, EmployeeInfoDTO employeeInfo, Long candidateId);

  List<RecruitmentDTO> getAllRecruitments();

  List<RecruitmentDTO> getAllRecruitmentsForCandidate(Long id);
}
