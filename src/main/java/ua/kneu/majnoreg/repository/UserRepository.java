package ua.kneu.majnoreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
