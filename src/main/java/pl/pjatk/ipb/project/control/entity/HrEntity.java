package pl.pjatk.ipb.project.control.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class HrEntity extends EmployeeEntity {
    @Column(name = "PHONE", nullable = false)
    private String phone;

    @OneToMany(
            targetEntity = RecruitmentEntity.class,
            mappedBy = "hrManager",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RecruitmentEntity> recruitments = new HashSet<>();

    @Builder
    public HrEntity(String email, String name, String surname, String password, LocalDateTime registerDateTime, String employeeIdentifier, String pesel, String nip, String address, EmployeeStatus status, String phone, Set<RecruitmentEntity> recruitments) {
        super(email, name, surname, password, registerDateTime, employeeIdentifier, pesel, nip, address, status);
        this.phone = phone;
        this.recruitments = recruitments;

        this.setRole(UserRole.HR_MANAGER);
    }
}
