package ua.kneu.majnoreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.Declaration;

public interface DeclarationRepository extends JpaRepository<Declaration, Integer> {

}
