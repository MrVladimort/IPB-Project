package pl.pjatk.ipb.project.control.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EmployeeEntity extends UserEntity {
  @Column(name = "EMPLOYEE_IDENTIFIER", nullable = false, unique = true)
  private String employeeIdentifier;

  @Column(name = "PESEL", nullable = false, unique = true)
  private String pesel;

  @Column(name = "NIP", nullable = false, unique = true)
  private String nip;

  @Column(name = "ADDRESS", nullable = false)
  private String address;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private EmployeeStatus status;

  public EmployeeEntity(String email, String name, String surname, String password,
      LocalDateTime registerDateTime, String employeeIdentifier, String pesel, String nip,
      String address, EmployeeStatus status) {
    super(email, name, surname, password, registerDateTime);
    this.employeeIdentifier = employeeIdentifier;
    this.pesel = pesel;
    this.nip = nip;
    this.address = address;
    this.status = status;

    this.setRole(UserRole.EMPLOYEE);
  }

  public enum EmployeeStatus {
    WORKING,
    PENDING
  }
}
