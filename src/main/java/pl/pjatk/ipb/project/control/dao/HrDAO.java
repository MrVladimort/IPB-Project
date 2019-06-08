package pl.pjatk.ipb.project.control.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pjatk.ipb.project.control.entity.HrEntity;

public interface HrDAO extends CrudRepository<HrEntity, Long> {

}
