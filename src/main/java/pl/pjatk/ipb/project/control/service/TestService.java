package pl.pjatk.ipb.project.control.service;

import pl.pjatk.ipb.project.boundary.dto.TestDTO;

import java.util.List;

public interface TestService {
    List<TestDTO> getTests();

    Double checkTestsResult(List<TestDTO> tests);
}
