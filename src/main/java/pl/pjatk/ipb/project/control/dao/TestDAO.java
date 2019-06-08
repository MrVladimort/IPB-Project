package pl.pjatk.ipb.project.control.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pl.pjatk.ipb.project.control.entity.TestEntity;

public interface TestDAO extends CrudRepository<TestEntity, Long> {
  Optional<TestEntity> findByQuestion(String question);
}
