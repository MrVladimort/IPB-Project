package pl.pjatk.ipb.project.control.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pl.pjatk.ipb.project.control.entity.RecruitmentEntity;
import pl.pjatk.ipb.project.control.entity.RecruitmentEntity.RecruitmentStatus;

public interface RecruitmentDAO extends CrudRepository<RecruitmentEntity, Long> {
  Optional<RecruitmentEntity> findByIdAndStatus(Long id, RecruitmentStatus status);
  List<RecruitmentEntity> findAllByCandidate_Id(Long id);
}
