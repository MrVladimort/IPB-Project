package pl.pjatk.ipb.project.control.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
@Entity
public class TeamLeadEntity extends EmployeeEntity {
    @OneToMany(
            targetEntity = InterviewEntity.class,
            mappedBy = "teamLead",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<InterviewEntity> interviews = new HashSet<>();

    @Builder
    public TeamLeadEntity(String email, String name, String surname, String password, LocalDateTime registerDateTime, String employeeIdentifier, String pesel, String nip, String address, EmployeeStatus status, Set<InterviewEntity> interviews) {
        super(email, name, surname, password, registerDateTime, employeeIdentifier, pesel, nip, address, status);
        this.interviews = interviews;

        this.setRole(UserRole.TEAM_LEAD);
    }
}
