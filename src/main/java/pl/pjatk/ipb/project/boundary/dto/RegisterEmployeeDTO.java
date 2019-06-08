package pl.pjatk.ipb.project.boundary.dto;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class RegisterEmployeeDTO {
  private String name;
  private String surname;
  private String email;
  private String password;
  private String repeatPassword;
  private String pesel;
  private String nip;
  private String address;
}
