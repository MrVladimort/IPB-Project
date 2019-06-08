package pl.pjatk.ipb.project.control.entity;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
public class TestEntity {
    @Id
    @Column(name = "TESTS_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_SQ")
    @SequenceGenerator(name = "T_SQ", sequenceName = "TESTS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "ANSWERS")
    @ElementCollection
    private List<String> answers;

    @Column(name = "CORRECT_ANSWER")
    private String correctAnswer;

    @Builder
    public TestEntity(String question, List<String> answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
}
