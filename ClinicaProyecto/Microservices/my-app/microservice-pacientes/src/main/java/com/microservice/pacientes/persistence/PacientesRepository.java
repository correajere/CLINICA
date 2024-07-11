package com.microservice.pacientes.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.microservice.pacientes.entities.Pacientes;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacientesRepository extends CrudRepository<Pacientes, Long> {

    // De las dos formas da lo mismo
    //@Query("SELECT p FROM Pacientes p Wwhere p.turnoId = :idTurno")
    //List<Pacientes> findAllPacientes(Long idTurno);

    // Este método lo toma automaticamente JPA por su anotación
    List<Pacientes> findAllByTurnoId(Long idTurno);
}
