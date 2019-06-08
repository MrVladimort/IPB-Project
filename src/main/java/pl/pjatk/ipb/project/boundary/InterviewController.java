package pl.pjatk.ipb.project.boundary;

import java.time.LocalDate;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.ipb.project.boundary.dto.InterviewDTO;
import pl.pjatk.ipb.project.boundary.dto.RecruitmentDTO;
import pl.pjatk.ipb.project.control.security.CurrentUser;
import pl.pjatk.ipb.project.control.security.UserPrincipal;
import pl.pjatk.ipb.project.control.service.InterviewService;

@RestController
@RequestMapping({"/api/interviews"})
@RequiredArgsConstructor
@Slf4j
public class InterviewController {
  @NonNull private InterviewService interviewService;

  @GetMapping("/team-lead")
  public ResponseEntity<List<InterviewDTO>> getAllInterviewByTeamLeadId(@RequestParam Long teamLeadId) {
    return ResponseEntity.ok(interviewService.getAllInterviewByTeamLeadId(teamLeadId));
  }

  @GetMapping("/recruitment")
  public ResponseEntity<InterviewDTO> getInterviewByRecruitmentId(@RequestParam Long recruitmentId) {
    return ResponseEntity.ok(interviewService.getInterviewByRecruitmentId(recruitmentId));
  }

  @GetMapping
  public ResponseEntity<List<InterviewDTO>> getAllInterviews() {
    return ResponseEntity.ok(interviewService.getAllInterviews());
  }

  @PostMapping
  public ResponseEntity<InterviewDTO> createInterview(@CurrentUser UserPrincipal candidate, @RequestParam Long recruitmentId, @RequestBody InterviewDTO dateAndTime) {
    return ResponseEntity.ok(interviewService.createInterview(candidate.getId(), recruitmentId, dateAndTime));
  }

  @PutMapping("/accept")
  public ResponseEntity<InterviewDTO> acceptInterview(@CurrentUser UserPrincipal hrManager, @RequestParam Long interviewId, @RequestParam Long teamLeadId) {
    return ResponseEntity.ok(interviewService.acceptInterview(interviewId, teamLeadId));
  }

  @PutMapping("/refuse")
  public ResponseEntity<InterviewDTO> refuseInterview(
      @RequestParam Long interviewId) {
    return ResponseEntity.ok(
        interviewService.refuse(interviewId));
  }

  @GetMapping("/user")
  public ResponseEntity<List<InterviewDTO>> getAllInterviewByCandidateId(@CurrentUser UserPrincipal candidate) {
    return ResponseEntity.ok(interviewService.getAllInterviewByCandidateId(candidate.getId()));
  }

  @GetMapping("/date")
  public ResponseEntity<List<InterviewDTO>> getAllInterviewByDate(@RequestParam LocalDate date) {
    return ResponseEntity.ok(interviewService.getAllInterviewByDate(date));
  }

  @GetMapping("/time")
  public ResponseEntity<List<String>> getInterviewTimes() {
    return ResponseEntity.ok(
        List.of(
            "8:00-9:00", "9:00-10:00", "11:00-12:00", "15:00-16:00", "16:00-17:00", "18:00-19:00"));
  }
}
