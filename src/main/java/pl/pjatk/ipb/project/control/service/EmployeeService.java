package pl.pjatk.ipb.project.control.service;

import java.util.List;

import pl.pjatk.ipb.project.boundary.dto.EmployeeDTO;
import pl.pjatk.ipb.project.boundary.dto.HrDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterHrDTO;
import pl.pjatk.ipb.project.boundary.dto.RegisterTeamLeadDTO;
import pl.pjatk.ipb.project.boundary.dto.TeamLeadDTO;

public interface EmployeeService {
    HrDTO createHrManager(RegisterHrDTO hrDTO);

    TeamLeadDTO createTeamLead(RegisterTeamLeadDTO hrDTO);

    EmployeeDTO activateEmployee(Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    List<HrDTO> getAllHrs();

    List<TeamLeadDTO> getAllTeamLeads();
}
