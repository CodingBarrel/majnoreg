package ua.kneu.majnoreg.repository.dict;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kneu.majnoreg.entity.dict.PropertyType;

public interface DeclarationPropertyTypeRepository extends JpaRepository<PropertyType, Integer> {

}
