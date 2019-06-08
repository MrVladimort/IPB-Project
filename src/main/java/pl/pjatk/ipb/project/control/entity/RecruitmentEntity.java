package pl.pjatk.ipb.project.control.entity;

import javax.persistence.*;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
public class RecruitmentEntity {
  @Id
  @Column(name = "RECRUITMENT_ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_SQ")
  @SequenceGenerator(name = "R_SQ", sequenceName = "RECRUITMENTS_SEQ", allocationSize = 1)
  private Long id;

  @ManyToOne(targetEntity = CandidateEntity.class)
  @JoinColumn(name = "CANDIDATE_ID", referencedColumnName = "USER_ID", nullable = false)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private CandidateEntity candidate;

  @ManyToOne(targetEntity = HrEntity.class)
  @JoinColumn(name = "HR_ID", referencedColumnName = "USER_ID")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private HrEntity hrManager;

  @ManyToOne(targetEntity = JobOfferEntity.class)
  @JoinColumn(name = "JOB_OFFER_ID", referencedColumnName = "JOB_OFFER_ID", nullable = false)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private JobOfferEntity jobOffer;

  @OneToMany(
  targetEntity = InterviewEntity.class,
  mappedBy = "recruitment",
  cascade = CascadeType.ALL,
  fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private Set<InterviewEntity> interviews = new HashSet<>();

  @Column(name = "TEST_RESULT_PERCENT")
  private Double testResultPercent;

  @Column(name = "LINK_TO_CV")
  private String linkToCV;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS")
  private RecruitmentStatus status;

  public enum RecruitmentStatus {
    PENDING,
    ACCEPTED,
    REFUSED,
    COMPLETED,
  }

  @Builder
  public RecruitmentEntity(CandidateEntity candidate,
      HrEntity hrManager, JobOfferEntity jobOffer,
      Set<InterviewEntity> interviews, Double testResultPercent, String linkToCV,
      RecruitmentStatus status) {
    this.candidate = candidate;
    this.hrManager = hrManager;
    this.jobOffer = jobOffer;
    this.interviews = interviews;
    this.testResultPercent = testResultPercent;
    this.linkToCV = linkToCV;
    this.status = status;
  }
}
