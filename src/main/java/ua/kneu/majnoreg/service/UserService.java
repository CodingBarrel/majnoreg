package ua.kneu.majnoreg.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.kneu.majnoreg.entity.User;
import ua.kneu.majnoreg.entity.dict.UserRole;
import ua.kneu.majnoreg.repository.UserRepository;
import ua.kneu.majnoreg.repository.dict.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private static final int DEFAULT_ROLE_ID = 1;

    public void create(User user) {
        log.info("Request to create user: " + user);
        if (userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified id already exists");
        }
        Optional<UserRole> role = userRoleRepository.findById(DEFAULT_ROLE_ID);
        if (role.isPresent()) {
            user.setRole(role.get());
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Default role not found");
        }
        userRepository.save(user);
    }

    public User findById(int id){
        log.info("Request to find user id: " + id);
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public UserRole findRoleById(int id){
        log.info("Request to find user role id: " + id);
        return userRoleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User role not found"));
    }

    public List<User> findAll(){
        log.info("Request to find all users");
        return userRepository.findAll();
    }

    public List<UserRole> findAllRoles(){
        log.info("Request to find all user roles");
        return userRoleRepository.findAll();
    }

    public void update(User user){
        log.info("Request to update user: " + user);
        if (userRepository.existsById(user.getId())){
            userRepository.save(user);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    public void deleteById(int id){
        log.info("Request to delete user id: " + id);
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }
}
