package pl.pjatk.ipb.project.control.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
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
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserEntity {
  @Id
  @Column(name = "USER_ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "U_SQ")
  @SequenceGenerator(name = "U_SQ", sequenceName = "USERS_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "SURNAME", nullable = false)
  private String surname;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "USER_ROLE")
  private UserRole role;

  @Column(name = "REGISTER_DATE_TIME")
  private LocalDateTime registerDateTime = LocalDateTime.now();

  public enum UserRole {
    HR_MANAGER,
    TEAM_LEAD,
    PROJECT_MANAGER,
    CANDIDATE,
    EMPLOYEE
  }

  public UserEntity(String email, String name, String surname, String password,
      LocalDateTime registerDateTime) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.password = password;
    this.registerDateTime = registerDateTime;
  }
}
