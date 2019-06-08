package pl.pjatk.ipb.project.boundary;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.ipb.project.boundary.dto.JobOfferDTO;
import pl.pjatk.ipb.project.control.service.JobOfferService;

@RestController
@RequestMapping({"/api/jobs"})
@RequiredArgsConstructor
@Slf4j
public class JobOfferController {
  @NonNull JobOfferService jobOfferService;

  @GetMapping
  public ResponseEntity<List<JobOfferDTO>> getAllJobOffers() {
    return ResponseEntity.ok(jobOfferService.getAllJobOffers());
  }
}
