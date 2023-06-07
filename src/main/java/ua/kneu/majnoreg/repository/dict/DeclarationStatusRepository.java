package ua.kneu.majnoreg.repository.dict;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.dict.DeclarationStatus;

public interface DeclarationStatusRepository extends JpaRepository<DeclarationStatus, Integer> {
}
