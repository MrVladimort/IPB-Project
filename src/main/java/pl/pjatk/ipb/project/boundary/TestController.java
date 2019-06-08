package pl.pjatk.ipb.project.boundary;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.ipb.project.boundary.dto.TestDTO;
import pl.pjatk.ipb.project.control.security.UserPrincipal;
import pl.pjatk.ipb.project.control.service.CandidateService;
import pl.pjatk.ipb.project.control.service.RecruitmentService;
import pl.pjatk.ipb.project.control.service.TestService;
import pl.pjatk.ipb.project.control.security.CurrentUser;

import java.util.List;

@RestController
@RequestMapping({"/api/tests"})
@RequiredArgsConstructor
@Slf4j
public class TestController {
    @NonNull private TestService testService;
    @NonNull private RecruitmentService recruitmentService;

    @GetMapping
    public ResponseEntity<List<TestDTO>> getTests() {
        return ResponseEntity.ok(testService.getTests());
    }
}
