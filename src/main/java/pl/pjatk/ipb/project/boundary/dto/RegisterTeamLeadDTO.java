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
public class RegisterTeamLeadDTO extends RegisterEmployeeDTO {
  @Builder
  public RegisterTeamLeadDTO(String name, String surname, String email, String password,
      String repeatPassword, String employeeIdentifier, String pesel, String nip,
      String address) {
    super(name, surname, email, password, repeatPassword, pesel, nip, address);
  }
}
