package pl.pjatk.ipb.project.control.service;

import pl.pjatk.ipb.project.boundary.dto.CandidateDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterCandidateDTO;

import java.util.List;

public interface CandidateService {
    CandidateDTO registerCandidate(RegisterCandidateDTO registerCandidateDto);

    List<CandidateDTO> getAllCandidates();
}
