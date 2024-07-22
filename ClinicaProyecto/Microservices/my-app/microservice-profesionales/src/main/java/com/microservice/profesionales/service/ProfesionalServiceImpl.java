package com.microservice.profesionales.service;

import com.microservice.profesionales.controller.requests.ModifyProfesionalRequest;
import com.microservice.profesionales.controller.requests.ModifyStateRequest;
import com.microservice.profesionales.entities.Profesional;
import com.microservice.profesionales.persistence.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalServiceImpl implements IProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalesRepository;

    @Override
    public List<Profesional> findAll() {
        return (List<Profesional>) profesionalesRepository.findAll();
    }

    @Override
    public Optional<Profesional> findById(Long id) {
        return profesionalesRepository.findById(id);
    }

    @Override
    public Profesional save(Profesional profesional) {
        return profesionalesRepository.save(profesional);
    }

    @Override
    public void deleteById(Long id) {
        profesionalesRepository.deleteById(id);
    }

    @Override
    public void update(Long id, ModifyProfesionalRequest modifyProfesionalRequest) {
        Optional<Profesional> optionalEntity = findById(id);

        if (optionalEntity.isPresent()) {
            Profesional updateEntity = optionalEntity.get();

            updateEntity.setName(modifyProfesionalRequest.getName() != null ?
                    modifyProfesionalRequest.getName() : updateEntity.getName());
            updateEntity.setLastName(modifyProfesionalRequest.getLastName() != null ?
                    modifyProfesionalRequest.getLastName() : updateEntity.getLastName());
            updateEntity.setPhoneNumber(modifyProfesionalRequest.getPhoneNumber() != null ?
                    modifyProfesionalRequest.getPhoneNumber() : updateEntity.getPhoneNumber());
            updateEntity.setEmail(modifyProfesionalRequest.getEmail() != null ?
                    modifyProfesionalRequest.getEmail() : updateEntity.getEmail());
            updateEntity.setLicense(modifyProfesionalRequest.getLicense() != null ?
                    modifyProfesionalRequest.getLicense() : updateEntity.getLicense());
            updateEntity.setSpecialty(modifyProfesionalRequest.getSpecialty() != null ?
                    modifyProfesionalRequest.getSpecialty() : updateEntity.getSpecialty());
            updateEntity.setSpecialtyLicense(modifyProfesionalRequest.getSpecialtyLicense() != null ?
                    modifyProfesionalRequest.getSpecialtyLicense() : updateEntity.getSpecialtyLicense());
            updateEntity.setSignature(modifyProfesionalRequest.getSignature() != null ?
                    modifyProfesionalRequest.getSignature() : updateEntity.getSignature());
            updateEntity.setStateBetweenEncounter(modifyProfesionalRequest.getStateBetweenEncounter() != null ?
                    modifyProfesionalRequest.getStateBetweenEncounter() : updateEntity.getStateBetweenEncounter());
            updateEntity.setStateOnlineEncounter(modifyProfesionalRequest.getStateOnlineEncounter() != null ?
                    modifyProfesionalRequest.getStateOnlineEncounter() : updateEntity.getStateOnlineEncounter());

            save(updateEntity);
        }
    }

    @Override
    public void updateState(Long id, ModifyStateRequest modifyStateRequest) {
        Optional<Profesional> optionalEntity = findById(id);

        if (optionalEntity.isPresent()) {
            Profesional updateEntity = optionalEntity.get();

            updateEntity.setStateBetweenEncounter(modifyStateRequest.getStateBetweenEncounter() != null ?
                    modifyStateRequest.getStateBetweenEncounter() : updateEntity.getStateBetweenEncounter());
            updateEntity.setStateOnlineEncounter(modifyStateRequest.getStateOnlineEncounter() != null ?
                    modifyStateRequest.getStateOnlineEncounter() : updateEntity.getStateOnlineEncounter());

            save(updateEntity);
        }
    }
}
