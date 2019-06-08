package pl.pjatk.ipb.project.control.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
public class JobOfferEntity {
  @Id
  @Column(name = "JOB_OFFER_ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JO_SQ")
  @SequenceGenerator(name = "JO_SQ", sequenceName = "JOB_OFFERS_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "POSITION")
  private String position;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "SALARY")
  private Integer salary;

  @OneToMany(
      targetEntity = RecruitmentEntity.class,
      mappedBy = "jobOffer",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private Set<RecruitmentEntity> recruitments = new HashSet<>();

  @Builder
  public JobOfferEntity(String name, String position, String description, Integer salary,
      Set<RecruitmentEntity> recruitments) {
    this.name = name;
    this.position = position;
    this.description = description;
    this.salary = salary;
    this.recruitments = recruitments;
  }
}
