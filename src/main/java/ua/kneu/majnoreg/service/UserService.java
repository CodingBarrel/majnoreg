package ua.kneu.majnoreg.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.kneu.majnoreg.entity.UserInformation;
import ua.kneu.majnoreg.entity.UserCredentials;
import ua.kneu.majnoreg.entity.dict.UserRole;
import ua.kneu.majnoreg.repository.UserCredentialsRepository;
import ua.kneu.majnoreg.repository.UserInformationRepository;
import ua.kneu.majnoreg.repository.dict.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInformationRepository informationRepository;
    private final UserCredentialsRepository credentialsRepository;
    private final UserRoleRepository roleRepository;

    private static final int DEFAULT_ROLE_ID = 1;

    public void create(UserInformation userInformation) {
        log.info("Request to create user: " + userInformation);
        if (informationRepository.existsById(userInformation.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified id already exists");
        }
        Optional<UserRole> role = roleRepository.findById(DEFAULT_ROLE_ID);
        if (role.isPresent()) {
            userInformation.setRole(role.get());
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Default role not found");
        }
        informationRepository.save(userInformation);
    }

    public void createCredentials(UserCredentials credentials){
        log.info("Request to create user credentials: " + credentials);
        credentialsRepository.save(credentials);
    }

    public UserInformation findById(int id){
        log.info("Request to find user id: " + id);
        return informationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public UserRole findRoleById(int id){
        log.info("Request to find user role id: " + id);
        return roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User role not found"));
    }

    public List<UserInformation> findAll(){
        log.info("Request to find all users");
        return informationRepository.findAll();
    }

    public List<UserRole> findAllRoles(){
        log.info("Request to find all user roles");
        return roleRepository.findAll();
    }

    public void update(UserInformation userInformation){
        log.info("Request to update user: " + userInformation);
        if (informationRepository.existsById(userInformation.getId())){
            informationRepository.save(userInformation);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    public void deleteById(int id){
        log.info("Request to delete user id: " + id);
        if (informationRepository.existsById(id)){
            informationRepository.deleteById(id);
        }
        if (credentialsRepository.existsById(id)){
            credentialsRepository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }
}
