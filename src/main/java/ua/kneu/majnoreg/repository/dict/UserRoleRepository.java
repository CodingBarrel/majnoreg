package ua.kneu.majnoreg.repository.dict;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.dict.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
