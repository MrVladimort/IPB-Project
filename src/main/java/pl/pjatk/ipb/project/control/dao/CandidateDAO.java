package pl.pjatk.ipb.project.control.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pjatk.ipb.project.control.entity.CandidateEntity;

public interface CandidateDAO extends CrudRepository<CandidateEntity, Long> {
}
