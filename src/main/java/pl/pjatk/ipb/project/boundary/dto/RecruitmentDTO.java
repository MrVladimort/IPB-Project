package pl.pjatk.ipb.project.boundary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RecruitmentDTO {
  private Long id;
  private JobOfferDTO jobOffer;
  private CandidateDTO candidate;
  private List<InterviewDTO> interviews;
  private Double testResultPercent;
  private String status;

  private HrDTO hrManager;
  private String linkToCV;

  @Builder
  public RecruitmentDTO(Long id, JobOfferDTO jobOffer, CandidateDTO candidate, List<InterviewDTO> interviews, Double testResultPercent, String status, HrDTO hrManager, String linkToCV) {
    this.id = id;
    this.jobOffer = jobOffer;
    this.candidate = candidate;
    this.interviews = interviews;
    this.testResultPercent = testResultPercent;
    this.status = status;
    this.hrManager = hrManager;
    this.linkToCV = linkToCV;
  }
}
