package pl.pjatk.ipb.project.control.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.pjatk.ipb.project.boundary.dto.JobOfferDTO;
import pl.pjatk.ipb.project.control.dao.JobOfferDAO;
import pl.pjatk.ipb.project.control.entity.JobOfferEntity;
import pl.pjatk.ipb.project.control.mapper.ProjectMapper;
import pl.pjatk.ipb.project.control.service.JobOfferService;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {
  @NonNull JobOfferDAO jobOfferDAO;
  @NonNull ProjectMapper mapper;

  @Override
  public List<JobOfferDTO> getAllJobOffers() {
    return StreamSupport.stream(jobOfferDAO.findAll().spliterator(), false)
        .map(mapper::jobOfferEntityFromDto)
        .collect(Collectors.toList());
  }

  @Bean
  public void loadJobOffersData() {
    jobOfferDAO.save(
        JobOfferEntity.builder()
            .name("Super offer")
            .position("Rabotnik")
            .description("Budesz rabotat'")
            .salary(2700)
            .build());

    jobOfferDAO.save(
        JobOfferEntity.builder()
            .name("Super offer 2")
            .position("Keker")
            .description("Budesz kekat'")
            .salary(3000)
            .build());

    jobOfferDAO.save(
        JobOfferEntity.builder()
            .name("Super offer 2")
            .position("Beker")
            .description("Budesz bekat'")
            .salary(4500)
            .build());

    jobOfferDAO.save(
        JobOfferEntity.builder()
            .name("Super offer 4")
            .position("Fufuker")
            .description("Budesz fufukat'")
            .salary(5000)
            .build());
  }
}
