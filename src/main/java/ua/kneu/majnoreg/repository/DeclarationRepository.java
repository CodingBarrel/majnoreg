package ua.kneu.majnoreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.Declaration;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Integer> {
    List<Declaration> findByUserInformation_Id(int id);
}
