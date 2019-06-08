package pl.pjatk.ipb.project.control.service;

import java.time.LocalDate;
import java.util.List;
import pl.pjatk.ipb.project.boundary.dto.InterviewDTO;
import pl.pjatk.ipb.project.boundary.dto.RecruitmentDTO;

public interface InterviewService {
  InterviewDTO getInterviewByRecruitmentId(Long recruitmentId);

  List<InterviewDTO> getAllInterviews();

  List<InterviewDTO> getAllInterviewByTeamLeadId(Long teamLeadId);

  List<InterviewDTO> getAllInterviewByCandidateId(Long candidateId);

  List<InterviewDTO> getAllInterviewByDate(LocalDate date);

  InterviewDTO createInterview(Long candidateId, Long recruitmentId, InterviewDTO dateAndTime);

  InterviewDTO acceptInterview(Long interviewId, Long teamLeadId);

  InterviewDTO refuse(Long interviewId);

  InterviewDTO complete(Long interviewId, String opinion);
}
