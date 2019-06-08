package pl.pjatk.ipb.project.control.entity;

import java.util.HashSet;
import java.util.Set;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@Entity
public class CandidateEntity extends UserEntity {
    @Column(name = "PHONE")
    private String phone;

    @OneToMany(
        targetEntity = RecruitmentEntity.class,
        mappedBy = "candidate",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<RecruitmentEntity> recruitments = new HashSet<>();

    @Builder
    public CandidateEntity(String email, String name, String surname, String password, LocalDateTime registerDateTime, String phone, Set<RecruitmentEntity> recruitments) {
        super(email, name, surname, password, registerDateTime);
        this.phone = phone;
        this.recruitments = recruitments;

        this.setRole(UserRole.CANDIDATE);
    }
}
