package ua.kneu.majnoreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.UserCredentials;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByLogin(String login);
    boolean existsByLogin(String name);
}
