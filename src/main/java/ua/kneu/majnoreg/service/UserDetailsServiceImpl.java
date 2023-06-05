package ua.kneu.majnoreg.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.kneu.majnoreg.repository.UserCredentialsRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCredentialsRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User with provided login not found"));
    }

}
