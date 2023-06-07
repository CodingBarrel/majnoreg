package ua.kneu.majnoreg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kneu.majnoreg.entity.Declaration;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Integer> {
    List<Declaration> findByUserInformation_Id(int id);

    Page<Declaration> findByPropertyTypeIdAndAddressContaining(Integer propertyTypeId, String address, Pageable pageable);



}
