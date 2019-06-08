package pl.pjatk.ipb.project.boundary.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RegisterCandidateDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String repeatPassword;
    private String phone;

    @Builder
    public RegisterCandidateDTO(String name, String surname, String email, String password,
        String repeatPassword, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.phone = phone;
    }
}
