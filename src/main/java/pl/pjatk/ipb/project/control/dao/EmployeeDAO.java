package pl.pjatk.ipb.project.control.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pjatk.ipb.project.control.entity.EmployeeEntity;

public interface EmployeeDAO extends CrudRepository<EmployeeEntity, Long> {

}
