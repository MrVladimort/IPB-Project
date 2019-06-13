package pl.pjatk.ipb.project.boundary.dto;

import java.time.LocalDate;

import lombok.Builder;
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
public class InterviewDTO {
    private Long id;
    private CandidateDTO candidate;
    private TeamLeadDTO teamLead;
    private JobOfferDTO jobOffer;
    private String time;
    private LocalDate date;
    private String opinion;
    private String status;

    @Builder
    public InterviewDTO(Long id, CandidateDTO candidate, TeamLeadDTO teamLead, JobOfferDTO jobOffer, String time, LocalDate date, String opinion, String status) {
        this.id = id;
        this.candidate = candidate;
        this.teamLead = teamLead;
        this.jobOffer = jobOffer;
        this.time = time;
        this.date = date;
        this.opinion = opinion;
        this.status = status;
    }
}
