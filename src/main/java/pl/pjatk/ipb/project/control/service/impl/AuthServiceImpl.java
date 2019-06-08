package pl.pjatk.ipb.project.control.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pjatk.ipb.project.boundary.dto.CredentialsDTO;
import pl.pjatk.ipb.project.boundary.dto.UserDTO;
import pl.pjatk.ipb.project.control.mapper.ProjectMapper;
import pl.pjatk.ipb.project.control.security.JwtTokenProvider;
import pl.pjatk.ipb.project.control.security.UserPrincipal;
import pl.pjatk.ipb.project.control.service.AuthService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @NonNull AuthenticationManager authenticationManager;
    @NonNull JwtTokenProvider tokenProvider;
    @NonNull private ProjectMapper mapper;

    @Override
    public UserDTO login(CredentialsDTO loginDTO) {
        log.info("User credentials: {}", loginDTO);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        log.info("I'm here");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());

        log.info("Access token for user with credentials: {}, token: {}", authentication.getPrincipal(), jwt);

        UserDTO userDto = mapper.userDtoFromPrincipal((UserPrincipal) authentication.getPrincipal());
        userDto.setAccessToken(jwt);

        return userDto;
    }


    @Override
    public UserDTO getUserData(UserPrincipal currentUser) {
        String jwt = tokenProvider.generateToken(currentUser);
        UserDTO userDto = mapper.userDtoFromPrincipal(currentUser);
        userDto.setAccessToken(jwt);
        return userDto;
    }
}

