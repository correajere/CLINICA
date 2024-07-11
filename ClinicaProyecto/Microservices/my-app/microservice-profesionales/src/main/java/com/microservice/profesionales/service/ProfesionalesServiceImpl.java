package com.microservice.profesionales.service;

import com.microservice.profesionales.entities.Profesionales;
import com.microservice.profesionales.persistence.ProfesionalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalesServiceImpl implements IProfesionalesService{

    @Autowired
    private ProfesionalesRepository profesionalesRepository;

    @Override
    public List<Profesionales> findAll() {
        return (List<Profesionales>) profesionalesRepository.findAll();
    }

    @Override
    public Profesionales findById(Long id) {
        return profesionalesRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Profesionales profesionales) {
        profesionalesRepository.save(profesionales);
    }

    @Override
    public List<Profesionales> findByIdTurno(Long idTurno) {
        return profesionalesRepository.findAllByTurnoId(idTurno);
    }
}
