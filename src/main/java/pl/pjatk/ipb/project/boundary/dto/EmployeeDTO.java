package pl.pjatk.ipb.project.boundary.dto;

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
public class EmployeeDTO extends UserDTO {
  private String employeeIdentifier;
  private String pesel;
  private String nip;
  private String address;
  private String status;

  public EmployeeDTO(
      Long id,
      String name,
      String surname,
      String email,
      String role,
      String accessToken,
      String employeeIdentifier,
      String pesel,
      String nip,
      String address,
      String status) {
    super(id, name, surname, email, role, accessToken);
    this.employeeIdentifier = employeeIdentifier;
    this.pesel = pesel;
    this.nip = nip;
    this.address = address;
    this.status = status;
  }
}
