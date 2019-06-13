package pl.pjatk.ipb.project.control.entity;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
public class InterviewEntity {
  @Id
  @Column(name = "INTERVIEW_ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "I_SQ")
  @SequenceGenerator(name = "I_SQ", sequenceName = "INTERVIEWS_SEQ", allocationSize = 1)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private RecruitmentEntity recruitment;

  @ManyToOne(targetEntity = TeamLeadEntity.class)
  @JoinColumn(name = "TEAM_LEAD_ID", referencedColumnName = "USER_ID")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private TeamLeadEntity teamLead;

  @Column(name = "INTERVIEW_TIME", nullable = false)
  private String time;

  @Column(name = "INTERVIEW_DATE", nullable = false)
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS")
  private InterviewStatus status = InterviewStatus.PENDING;

  @Column private String opinion;

  @Builder
  public InterviewEntity(
      RecruitmentEntity recruitment,
      TeamLeadEntity teamLead,
      String time,
      LocalDate date,
      InterviewStatus status,
      String opinion) {
    this.recruitment = recruitment;
    this.teamLead = teamLead;
    this.time = time;
    this.date = date;
    this.status = status;
    this.opinion = opinion;
  }

  public enum InterviewStatus {
    PENDING,
    ACCEPTED,
    REFUSED,
    COMPLETED
  }
}
