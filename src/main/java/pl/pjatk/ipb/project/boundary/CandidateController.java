package pl.pjatk.ipb.project.boundary;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.ipb.project.boundary.dto.CandidateDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterCandidateDTO;
import pl.pjatk.ipb.project.control.security.CurrentUser;
import pl.pjatk.ipb.project.control.security.UserPrincipal;
import pl.pjatk.ipb.project.control.service.CandidateService;

import java.util.List;

@RestController
@RequestMapping({"/api/candidates"})
@RequiredArgsConstructor
@Slf4j
public class CandidateController {
    @NonNull private CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateDTO> registerCandidate(@RequestBody RegisterCandidateDTO registerCandidateDto) {
        return ResponseEntity.ok(candidateService.registerCandidate(registerCandidateDto));
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }
}
