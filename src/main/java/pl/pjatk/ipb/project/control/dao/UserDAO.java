package pl.pjatk.ipb.project.control.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.ipb.project.control.entity.CandidateEntity;
import pl.pjatk.ipb.project.control.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserDAO
        extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
