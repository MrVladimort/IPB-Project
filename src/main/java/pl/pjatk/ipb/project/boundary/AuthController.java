package pl.pjatk.ipb.project.boundary;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.ipb.project.boundary.dto.CredentialsDTO;
import pl.pjatk.ipb.project.control.security.UserPrincipal;
import pl.pjatk.ipb.project.boundary.dto.UserDTO;
import pl.pjatk.ipb.project.control.security.CurrentUser;
import pl.pjatk.ipb.project.control.service.AuthService;

@RestController
@RequestMapping({"/api/auth"})
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    @NonNull private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDto) {
        return ResponseEntity.ok(authService.login(credentialsDto));
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUserData(@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(authService.getUserData(currentUser));
    }
}
