package pl.pjatk.ipb.project.boundary.dto;

import javax.persistence.Column;
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
public class JobOfferDTO {
  private Long id;
  private String name;
  private String position;
  private String description;
  private Integer salary;


  @Builder
  public JobOfferDTO(Long id, String name, String position, String description,
      Integer salary) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.description = description;
    this.salary = salary;
  }
}
