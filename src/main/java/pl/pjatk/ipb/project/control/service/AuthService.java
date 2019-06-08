package pl.pjatk.ipb.project.control.service;

import pl.pjatk.ipb.project.boundary.dto.CredentialsDTO;
import pl.pjatk.ipb.project.control.security.UserPrincipal;
import pl.pjatk.ipb.project.boundary.dto.UserDTO;

public interface AuthService {
    UserDTO login(CredentialsDTO loginDTO);

    UserDTO getUserData(UserPrincipal currentUser);
}
