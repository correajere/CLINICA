package com.microservice.pacientes.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.microservice.pacientes.entities.Pacientes;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacientesRepository extends CrudRepository<Pacientes, Long> {

    List<Pacientes> findAllByTurnoId(Long idTurno);
}
