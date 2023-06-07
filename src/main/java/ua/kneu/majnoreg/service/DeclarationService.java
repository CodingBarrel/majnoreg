package ua.kneu.majnoreg.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.kneu.majnoreg.entity.Declaration;
import ua.kneu.majnoreg.entity.dict.PropertyType;
import ua.kneu.majnoreg.repository.DeclarationRepository;
import ua.kneu.majnoreg.repository.dict.DeclarationPropertyTypeRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeclarationService {
    private final DeclarationRepository declarationRepository;
    private final DeclarationPropertyTypeRepository propertyTypeRepository;

    public void create(Declaration declaration) {
        log.info("Request to create declaration: " + declaration);
        if (declarationRepository.existsById(declaration.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Declaration with specified id already exists");
        }
        declarationRepository.save(declaration);
    }

    public Declaration findById(int id){
        log.info("Request to find declaration id: " + id);
        return declarationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Declaration not found"));
    }

    public PropertyType findPropertyTypeById(int id){
        log.info("Request to find declaration property type id: " + id);
        return propertyTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Declaration property type not found"));

    }

    public List<Declaration> findDeclarationsByUserId(int id){
        return declarationRepository.findByUserInformation_Id(id);
    }

    public Page<Declaration> findByPropertyTypeIdAndAddressContaining(Integer propertyTypeId, String address, Pageable pageable){
        return declarationRepository.findByPropertyTypeIdAndAddressContaining(propertyTypeId, address, pageable);
    }

    public List<Declaration> findAll(){
        log.info("Request to find all declarations");
        return declarationRepository.findAll();
    }

    public Page<Declaration> findAll(Pageable pageable){
        log.info("Request to find all declarations");
        return declarationRepository.findAll(pageable);
    }

    public List<PropertyType> findAllPropertyTypes(){
        log.info("Request to find all declaration property types");
        return propertyTypeRepository.findAll();
    }

    public void update(Declaration declaration){
        log.info("Request to update declaration: " + declaration);
        if (declarationRepository.existsById(declaration.getId())){
            declarationRepository.save(declaration);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Declaration not found");
        }
    }

    public void deleteById(int id){
        log.info("Request to delete declaration id: " + id);
        if (declarationRepository.existsById(id)){
            declarationRepository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Declaration not found");
        }
    }

}
