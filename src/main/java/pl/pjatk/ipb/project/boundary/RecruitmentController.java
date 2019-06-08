package pl.pjatk.ipb.project.boundary;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.ipb.project.boundary.dto.EmployeeDTO;
import pl.pjatk.ipb.project.boundary.dto.EmployeeInfoDTO;
import pl.pjatk.ipb.project.boundary.dto.RecruitmentDTO;
import pl.pjatk.ipb.project.boundary.dto.TestDTO;
import pl.pjatk.ipb.project.control.security.CurrentUser;
import pl.pjatk.ipb.project.control.security.UserPrincipal;
import pl.pjatk.ipb.project.control.service.RecruitmentService;
import pl.pjatk.ipb.project.control.service.TestService;

@RestController
@RequestMapping({"/api/recruitments"})
@RequiredArgsConstructor
@Slf4j
public class RecruitmentController {
  @NonNull RecruitmentService recruitmentService;
  @NonNull TestService testService;

  @PutMapping("/cv")
  public ResponseEntity<RecruitmentDTO> addCv(
      @CurrentUser UserPrincipal currentUser, @RequestBody RecruitmentDTO idAndLink) {
    return ResponseEntity.ok(
        recruitmentService.addCvLink(
            idAndLink.getId(), currentUser.getId(), idAndLink.getLinkToCV()));
  }

  @PostMapping
  public ResponseEntity<RecruitmentDTO> createRecruitment(
      @CurrentUser UserPrincipal currentUser,
      @RequestParam Long jobOfferId,
      @RequestBody List<TestDTO> tests) {

    return ResponseEntity.ok(
        recruitmentService.createRecruitment(
            currentUser.getId(), jobOfferId, testService.checkTestsResult(tests)));
  }

  @PutMapping("/refuse")
  public ResponseEntity<RecruitmentDTO> refuseRecruitment(
      @CurrentUser UserPrincipal currentUser, @RequestParam Long recruitmentId) {
    return ResponseEntity.ok(recruitmentService.refuse(recruitmentId, currentUser.getId()));
  }

  @PutMapping("/accept")
  public ResponseEntity<RecruitmentDTO> acceptRecruitment(
      @CurrentUser UserPrincipal currentUser, @RequestParam Long recruitmentId) {
    return ResponseEntity.ok(recruitmentService.accept(recruitmentId, currentUser.getId()));
  }

  @PutMapping("/complete")
  public ResponseEntity<EmployeeDTO> completeRecruitment(
      @CurrentUser UserPrincipal currentUser, @RequestBody EmployeeInfoDTO employeeInfo,  @RequestParam Long recruitmentId) {
    return ResponseEntity.ok(recruitmentService.complete(recruitmentId, employeeInfo, currentUser.getId()));
  }

  @GetMapping
  public ResponseEntity<List<RecruitmentDTO>> getAllRecruitments() {
    return ResponseEntity.ok(recruitmentService.getAllRecruitments());
  }

  @GetMapping("/user")
  public ResponseEntity<List<RecruitmentDTO>> getAllRecruitmentsForCandidate(@CurrentUser UserPrincipal candidate) {
    return ResponseEntity.ok(recruitmentService.getAllRecruitmentsForCandidate(candidate.getId()));
  }
}
