package pl.pjatk.ipb.project.boundary.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class TestDTO {
    private Long id;
    private String question;
    private List<String> answers;
    private String submittedAnswer;

    @Builder
    public TestDTO(Long id, String question, List<String> answers, String submittedAnswer) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.submittedAnswer = submittedAnswer;
    }
}
