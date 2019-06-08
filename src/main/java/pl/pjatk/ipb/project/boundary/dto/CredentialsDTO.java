package pl.pjatk.ipb.project.boundary.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CredentialsDTO {
    private String email;
    private String password;

    @Builder
    public CredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
