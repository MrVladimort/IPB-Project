package pl.pjatk.ipb.project.boundary.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
public class TeamLeadDTO extends EmployeeDTO {

  @Builder

  public TeamLeadDTO(Long id, String name, String surname, String email, String role, String accessToken, String employeeIdentifier, String pesel, String nip, String address, String status) {
    super(id, name, surname, email, role, accessToken, employeeIdentifier, pesel, nip, address, status);
  }
}
