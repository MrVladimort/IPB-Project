package pl.pjatk.ipb.project.control.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pjatk.ipb.project.control.entity.JobOfferEntity;

public interface JobOfferDAO extends CrudRepository<JobOfferEntity, Long> {
}

