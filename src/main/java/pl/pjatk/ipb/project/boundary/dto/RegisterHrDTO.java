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
public class RegisterHrDTO extends RegisterEmployeeDTO {
  private String phone;

  @Builder
  public RegisterHrDTO(String name, String surname, String email, String password,
      String repeatPassword, String pesel, String nip, String address, String phone) {
    super(name, surname, email, password, repeatPassword, pesel, nip, address);
    this.phone = phone;
  }
}
