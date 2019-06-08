package pl.pjatk.ipb.project.boundary.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
public class CandidateDTO extends UserDTO {
    private String phone;

    @Builder
    public CandidateDTO(Long id, String name, String surname, String email, String role, String accessToken, String phone) {
        super(id, name, surname, email, role, accessToken);
        this.phone = phone;
    }
}
