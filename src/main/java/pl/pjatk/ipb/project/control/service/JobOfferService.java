package pl.pjatk.ipb.project.control.service;

import java.util.List;
import pl.pjatk.ipb.project.boundary.dto.JobOfferDTO;

public interface JobOfferService {
  List<JobOfferDTO> getAllJobOffers();
}
