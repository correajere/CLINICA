package com.microservice.turnos.persistence;

import com.microservice.turnos.entities.Turnos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TurnosRepository extends CrudRepository<Turnos, Long>{

    List<Turnos> findAllByProfesionalId(Long idProfesional);

    List<Turnos> findAllByPacienteId(Long idPaciente);
}
