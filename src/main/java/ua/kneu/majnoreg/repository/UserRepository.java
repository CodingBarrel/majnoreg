package ua.kneu.majnoreg.repository;

import org.springframework.data.repository.CrudRepository;
import ua.kneu.majnoreg.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
