package pl.pjatk.ipb.project.boundary;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.ipb.project.boundary.dto.EmployeeDTO;
import pl.pjatk.ipb.project.boundary.dto.HrDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterHrDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterTeamLeadDTO;
import pl.pjatk.ipb.project.boundary.dto.TeamLeadDTO;
import pl.pjatk.ipb.project.control.service.EmployeeService;

@RestController
@RequestMapping({"/api/employees"})
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
  @NonNull
  private EmployeeService employeeService;

  @PostMapping("/hr")
  public ResponseEntity<HrDTO> createHr(@RequestBody RegisterHrDTO registerHrDTO) {
    return ResponseEntity.ok(employeeService.createHrManager(registerHrDTO));
  }

  @PostMapping("/team-leads")
  public ResponseEntity<TeamLeadDTO> createTeamLead(@RequestBody RegisterTeamLeadDTO registerTeamLeadDTO) {
    return ResponseEntity.ok(employeeService.createTeamLead(registerTeamLeadDTO));
  }

  @GetMapping
  public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @GetMapping("/hr")
  public ResponseEntity<List<HrDTO>> getAllHrs() {
    return ResponseEntity.ok(employeeService.getAllHrs());
  }

  @GetMapping("/team-leads")
  public ResponseEntity<List<TeamLeadDTO>> getAllTeamLeads() {
    return ResponseEntity.ok(employeeService.getAllTeamLeads());
  }

  @PutMapping
  public ResponseEntity<EmployeeDTO> activateEmployee(@RequestParam Long employeeId) {
    return ResponseEntity.ok(employeeService.activateEmployee(employeeId));
  }
}
