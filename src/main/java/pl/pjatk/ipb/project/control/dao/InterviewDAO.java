package pl.pjatk.ipb.project.control.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pjatk.ipb.project.control.entity.InterviewEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InterviewDAO extends CrudRepository<InterviewEntity, Long> {
    Optional<InterviewEntity> findByRecruitment_Id(Long recruitmentId);
    List<InterviewEntity> findAllByTeamLead_Id(Long recruitmentId);
    List<InterviewEntity> findAllByRecruitment_Candidate_Id(Long candidateId);
    List<InterviewEntity> findAllByDate(LocalDate date);
}
