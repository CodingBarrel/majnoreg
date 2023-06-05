package ua.kneu.majnoreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.UserInformation;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {
}
